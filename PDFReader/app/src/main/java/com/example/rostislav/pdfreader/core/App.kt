package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.feature.main.MainPresenter
import com.example.rostislav.pdfreader.feature.main.Presenter
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.database.DatabaseManager
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager

class App : Application() {
    lateinit var network: Network
    lateinit var presenter: Presenter
    lateinit var database: Database

    override fun onCreate() {
        super.onCreate()
        network = NetworkManager()
        presenter = MainPresenter()
        database = DatabaseManager(applicationContext)
    }
}