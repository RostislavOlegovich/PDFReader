package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.network.Network
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(override var view: View? = null) : Presenter {
    private lateinit var network: Network
    private lateinit var database: Database

    override fun downloadView(string: String, context: Context) {
        super.downloadView(string, context)
        network = (context.applicationContext as App).network

        doAsync {
            network.connectToNetwork(string, context)
            uiThread {
                Thread.sleep(3000)
                getViewFromDatabase(context)
            }
        }
    }

    override fun getViewFromDatabase(context: Context) {
        database = (context.applicationContext as App).database
        view?.showView(database.getData())
    }
}