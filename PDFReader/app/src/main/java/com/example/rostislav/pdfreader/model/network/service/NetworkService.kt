package com.example.rostislav.pdfreader.model.network.service

import android.content.Intent
import android.os.HandlerThread
import com.example.rostislav.pdfreader.core.base.BaseObserver
import com.example.rostislav.pdfreader.core.base.ServiceLifecycleObserver
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.utils.NotificationUtils
import com.example.rostislav.pdfreader.utils.StringUtils

class NetworkService : Observer<Data> by BaseObserver(), ServiceLifecycleObserver(), ServiceHandler {
    private val listOfUrls = mutableListOf<String>()

    private lateinit var serviceHandler: ServiceHandlerImpl

    override fun onCreate() {
        super.onCreate()
        val thread = HandlerThread("Service")
        thread.start()
        serviceHandler = ServiceHandlerImpl(thread.looper, this)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        val msg = serviceHandler.obtainMessage()
        msg.arg1 = startId
        msg.obj = intent
        serviceHandler.sendMessage(msg)
    }

    override fun onObserve(data: Data) {
        startForeground(
            SERVICE_FOREGROUND_ID,
            NotificationUtils.createNotification(applicationContext, data.progress.toInt())
        )
    }

    override fun showNotification() {
        if (listOfUrls.isNotEmpty()) network.getObservable().subscribe(this)
    }

    override fun hideNotification() {
        network.getObservable().unsubscribe(this)
        stopForeground(true)
    }

    override fun startServiceForeground(intent: Intent) {
        val url = intent.getStringExtra(StringUtils.getExtraStringIntent())
        url?.let {
            listOfUrls.add(url)
            setupForeground()
            val bytes = network.downloadFromNetwork(url)
            network.stopNetworkService(bytes, url)
        }
    }

    override fun stopServiceForeground(int: Int) {
        listOfUrls.removeAt(INDEX)
        stopSelf(int)
    }

    private fun setupForeground() {
        startForeground(SERVICE_FOREGROUND_ID, NotificationUtils.createNotification(applicationContext))
        stopForeground(true)
    }

    companion object {
        private const val SERVICE_FOREGROUND_ID = 1000
        private const val INDEX = 0
    }
}