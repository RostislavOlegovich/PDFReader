package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.feature.main.MainPresenter
import com.example.rostislav.pdfreader.feature.main.Presenter
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
import java.util.concurrent.Executors

class App : Application() {
    lateinit var network: Network
    lateinit var presenter: Presenter
    val executor = Executors.newFixedThreadPool(3)

    override fun onCreate() {
        super.onCreate()
        network = NetworkManager()
        presenter = MainPresenter()
    }
}