package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.model.network.Network
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File

class MainPresenter(override var view: View? = null) : Presenter {
    private lateinit var network: Network

    override fun downloadView(string: String, context: Context) {
        super.downloadView(string, context)
        network = (context.applicationContext as App).network
        doAsync {
            network.connectToNetwork(string, context)
            uiThread {
                Thread.sleep(3000)
                val directory = context.filesDir
                val file = File(directory, "file")
                view?.showView(file)
            }
        }
    }
}