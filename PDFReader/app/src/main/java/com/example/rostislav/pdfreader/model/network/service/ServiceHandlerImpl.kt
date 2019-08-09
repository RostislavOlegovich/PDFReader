package com.example.rostislav.pdfreader.model.network.service

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message

class ServiceHandlerImpl(looper: Looper, private val serviceHandler: ServiceHandler) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        val intent = msg.obj as Intent
        val int = msg.arg1
        when (intent.action) {
            ACTION_START_FOREGROUND -> serviceHandler.startServiceForeground(intent)
            ACTION_STOP_SERVICE -> serviceHandler.stopServiceForeground(int)
        }
    }

    companion object {
        private const val ACTION_START_FOREGROUND = "start_service"
        private const val ACTION_STOP_SERVICE = "stop_service"
    }
}