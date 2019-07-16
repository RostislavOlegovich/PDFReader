package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.database.DatabaseManager
import com.example.rostislav.pdfreader.model.file.FileManager
import com.example.rostislav.pdfreader.model.file.FileManagerImpl
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
import com.example.rostislav.pdfreader.utils.system.ThreadCheker
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class App : Application() {
    lateinit var network: Network
    lateinit var database: Database
    lateinit var fileManager: FileManager

    val executor: ExecutorService =
        Executors.newFixedThreadPool(ThreadCheker.getNumberOfCores())

    override fun onCreate() {
        super.onCreate()
        network = NetworkManager()
        database = DatabaseManager(applicationContext)
        fileManager = FileManagerImpl()
    }
}