package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File

class MainPresenter(override var view: View? = null) : Presenter {

    private lateinit var networkManager: Network

    override fun downloadView(string: String, context: Context) {
        super.downloadView(string, context)

        networkManager = NetworkManager()

        doAsync {

            networkManager.connectToNetwork(string, context)

            uiThread {
                Thread.sleep(3000)
                val directory = context.filesDir
                val file = File(directory, "file")
                view?.showView(file)
            }

        }

    }


}