package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class App : Application() {
    lateinit var network: Network

    val executor: ExecutorService = Executors.newFixedThreadPool(3)

    override fun onCreate() {
        super.onCreate()
        network = NetworkManager()
    }
}