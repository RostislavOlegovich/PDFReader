package com.example.rostislav.pdfreader.model.network.service

import android.app.NotificationManager
import android.content.Intent
import com.example.rostislav.pdfreader.core.base.BaseService
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.utils.NotificationUtils
import com.example.rostislav.pdfreader.utils.getExtraStringIntent

class NetworkService : Observer<Data>, BaseService() {
    lateinit var notificationManager: NotificationManager

    override fun onObserve(data: Data) {
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, NotificationUtils.createNotification(applicationContext, data.progress.toInt()))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START_SERVICE -> startForegroundService(intent.getStringExtra(getExtraStringIntent()))
            ACTION_STOP_SERVICE -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
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
        private const val ACTION_START_SERVICE = "start_service"
        private const val ACTION_STOP_SERVICE = "stop_service"
        private const val SERVICE_FOREGROUND_ID = 1
    }
}