package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.database.DatabaseManager
import com.example.rostislav.pdfreader.model.file.FileManager
import com.example.rostislav.pdfreader.model.file.FileManagerImpl
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
import com.example.rostislav.pdfreader.model.presenter.PresenterManager
import com.example.rostislav.pdfreader.model.presenter.PresenterManagerImpl
import com.example.rostislav.pdfreader.repository.Repository
import com.example.rostislav.pdfreader.repository.RepositoryImpl
import com.example.rostislav.pdfreader.utils.system.getNumberOfCores
import com.facebook.stetho.Stetho
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class App : Application() {
    lateinit var repository: Repository
    lateinit var presenterManager: PresenterManager
    lateinit var database: Database
    lateinit var fileManager: FileManager
    lateinit var network: Network

    val executor: ExecutorService =
        Executors.newFixedThreadPool(getNumberOfCores())

    override fun onCreate() {
        super.onCreate()
        database = DatabaseManager(applicationContext)
        fileManager = FileManagerImpl(applicationContext)
        presenterManager = PresenterManagerImpl()
        network = NetworkManager(applicationContext)
        repository = RepositoryImpl(applicationContext)
        stethoInit()
    }

    private fun stethoInit() {
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        Stetho.initialize(initializerBuilder.build())
    }
}