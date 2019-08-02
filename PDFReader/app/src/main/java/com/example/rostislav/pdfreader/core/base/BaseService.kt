package com.example.rostislav.pdfreader.core.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.rostislav.pdfreader.core.di.ManagersInjection
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
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
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    fun stopService() {
        stopSelf()
    }
}