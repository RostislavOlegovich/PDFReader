package com.example.rostislav.pdfreader.core.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.rostislav.pdfreader.core.di.ManagersInjection
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
import com.example.rostislav.pdfreader.utils.NotificationUtils
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor

abstract class BaseService : Service() {
    lateinit var network: Network
    lateinit var executor: Executor

    override fun onCreate() {
        network = ManagersInjection.get<NetworkManager>()
        executor = ManagersInjection.get<ThreadPoolExecutor>()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        NotificationUtils.createNotificationChannel(applicationContext)
        startForeground(SERVICE_FOREGROUND_ID, NotificationUtils.createNotification(applicationContext))
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    companion object {
        private const val ACTION_START_SERVICE = "start_service"
        private const val ACTION_STOP_SERVICE = "stop_service"
        private const val SERVICE_FOREGROUND_ID = 1
    }
}