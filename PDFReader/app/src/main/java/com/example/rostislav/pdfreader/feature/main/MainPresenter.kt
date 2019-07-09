package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import android.os.Looper
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.model.network.Network
import java.io.File
import java.util.concurrent.ExecutorService

class MainPresenter(override var view: View? = null) : Presenter {
    private lateinit var network: Network
    private lateinit var executor: ExecutorService

    private val handler = android.os.Handler(Looper.getMainLooper())

    override fun downloadView(string: String, context: Context) {
        network = (context.applicationContext as App).network
        executor = (context.applicationContext as App).executor

        val runnable = Runnable { view?.showView(File(context.filesDir, "file")) }

        executor.execute {
            network.connectToNetwork(string, context)
            handler.postDelayed(runnable, 3000)
        }
    }
}