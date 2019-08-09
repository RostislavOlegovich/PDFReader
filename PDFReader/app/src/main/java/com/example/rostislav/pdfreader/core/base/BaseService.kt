package com.example.rostislav.pdfreader.core.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.rostislav.pdfreader.core.di.ManagersInjection
import com.example.rostislav.pdfreader.model.network.Network

abstract class BaseService : Service() {
    lateinit var network: Network

    override fun onCreate() {
        network = ManagersInjection.get()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        onStart(intent, startId)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}