package com.example.rostislav.pdfreader.core.configuration

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.example.rostislav.pdfreader.utils.system.getNumberOfCores
import com.facebook.stetho.Stetho
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object Configuration {
    lateinit var executors: ExecutorService
    lateinit var handler: Handler

    fun setupStetho(application: Application) {
        val initializerBuilder = Stetho.newInitializerBuilder(application)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(application))
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(application))
        Stetho.initialize(initializerBuilder.build())
    }

    fun setupAsync() {
        executors = Executors.newFixedThreadPool(getNumberOfCores())
        handler = Handler(Looper.getMainLooper())// Обязательно ли вызывать лупер туть?
    }
}