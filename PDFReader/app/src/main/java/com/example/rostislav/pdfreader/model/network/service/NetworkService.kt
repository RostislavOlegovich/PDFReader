package com.example.rostislav.pdfreader.model.network.service

import android.content.Intent
import androidx.lifecycle.LifecycleObserver
import com.example.rostislav.pdfreader.core.base.BaseService
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.utils.NotificationUtils
import com.example.rostislav.pdfreader.utils.StringUtils

class NetworkService : BaseService(), Observer<Data>, LifecycleObserver {
    private var isAppVisible = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        network.getObservable().subscribe(this)
        when (intent?.action) {
            ACTION_START_FOREGROUND -> startForegroundService(intent.getStringExtra(StringUtils.getExtraStringIntent()))
            ACTION_HIDE_NOTIFICATION -> isAppVisible = true
            ACTION_SHOW_NOTIFICATION -> isAppVisible = false
            ACTION_STOP_SERVICE -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onObserve(data: Data) {
        if (isAppVisible) {
            stopForeground(true)
        } else {
            startForeground(
                SERVICE_FOREGROUND_ID,
                NotificationUtils.createNotification(applicationContext, data.progress.toInt())
            )
        }
    }

    override fun onError(exception: Throwable) {
    }

    private fun startForegroundService(url: String?) {
        NotificationUtils.createNotificationChannel(applicationContext)
        startForeground(SERVICE_FOREGROUND_ID, NotificationUtils.createNotification(applicationContext))
        executor.execute {
            val bytes = network.downloadFromNetwork(url!!)
            network.returnBytesArray(bytes)
        }
    }

    companion object {
        private const val ACTION_START_FOREGROUND = "start_service"
        private const val ACTION_STOP_SERVICE = "stop_service"
        private const val ACTION_HIDE_NOTIFICATION = "hide"
        private const val ACTION_SHOW_NOTIFICATION = "show"
        private const val SERVICE_FOREGROUND_ID = 1000
    }
}