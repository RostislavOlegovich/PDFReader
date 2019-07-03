package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager

class App : Application(){

    lateinit var network: Network

    override fun onCreate() {
        super.onCreate()

        network = NetworkManager()
    }
}