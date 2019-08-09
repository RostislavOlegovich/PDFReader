package com.example.rostislav.pdfreader.model.network.service

import android.content.Intent

interface ServiceHandler {
    fun startServiceForeground(intent: Intent)

    fun stopServiceForeground(int: Int)
}