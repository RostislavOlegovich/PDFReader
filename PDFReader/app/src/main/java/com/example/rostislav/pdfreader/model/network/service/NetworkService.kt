package com.example.rostislav.pdfreader.model.network.service

import android.content.Intent
import com.example.rostislav.pdfreader.core.base.BaseService
import com.example.rostislav.pdfreader.utils.NotificationUtils
import com.example.rostislav.pdfreader.utils.getExtraStringIntent

class NetworkService : BaseService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START_SERVICE -> startForegroundService(intent.getStringExtra(getExtraStringIntent()))
            ACTION_STOP_SERVICE -> stopService()
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