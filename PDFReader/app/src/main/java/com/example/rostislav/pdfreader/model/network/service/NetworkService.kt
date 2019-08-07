package com.example.rostislav.pdfreader.model.network.service

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.rostislav.pdfreader.core.base.BaseService
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.utils.NotificationUtils
import com.example.rostislav.pdfreader.utils.StringUtils

class NetworkService : BaseService(), Observer<Data>, LifecycleObserver {
    private val list = mutableListOf<String>()

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (intent.action) {
                ACTION_START_FOREGROUND -> startForegroundService(intent.getStringExtra(StringUtils.getExtraStringIntent()))
                ACTION_STOP_SERVICE -> stopForegroundService()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onObserve(data: Data) {
        startForeground(
            SERVICE_FOREGROUND_ID,
            NotificationUtils.createNotification(applicationContext, data.progress.toInt())
        )
    }

    override fun onError(exception: Throwable) {
    }

    private fun stopForegroundService() {
        list.removeAt(INDEX)
        stopSelf()
    }

    private fun startForegroundService(url: String?) {
        url?.let {
            list.add(url)
            setupForegroundUtils()
            executor.execute {
                val bytes = network.downloadFromNetwork(url)
                network.stopNetworkService(bytes)
            }
        }
    }

    private fun setupForegroundUtils() {
        NotificationUtils.createNotificationChannel(applicationContext)
        startForeground(SERVICE_FOREGROUND_ID, NotificationUtils.createNotification(applicationContext))
        stopForeground(true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pshowNotification() {
        if (list.isNotEmpty()) network.getObservable().subscribe(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun phideNotification() {
        if (list.isNotEmpty()) {
            network.getObservable().unsubscribe(this)
            stopForeground(true)
        }
    }

    companion object {
        private const val ACTION_START_FOREGROUND = "start_service"
        private const val ACTION_STOP_SERVICE = "stop_service"
        private const val SERVICE_FOREGROUND_ID = 1000
        private const val INDEX = 0
    }
}