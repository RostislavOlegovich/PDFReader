package com.example.rostislav.pdfreader.model.network.service

import android.content.Intent
import com.example.rostislav.pdfreader.core.base.BaseService
import com.example.rostislav.pdfreader.utils.getExtraStringIntent

class NetworkService : BaseService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url = intent?.getStringExtra(getExtraStringIntent())
        executor.execute {
            val bytes = network.downloadFromNetwork(url!!)
            network.returnBytesArray(bytes)
        }
        return super.onStartCommand(intent, flags, startId)
    }
}