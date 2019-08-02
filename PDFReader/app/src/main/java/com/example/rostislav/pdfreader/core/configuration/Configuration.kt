package com.example.rostislav.pdfreader.core.configuration

import android.app.Application
import com.facebook.stetho.Stetho

object Configuration {
    fun setup(application: Application) {
        val initializerBuilder = Stetho.newInitializerBuilder(application)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(application))
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(application))
        Stetho.initialize(initializerBuilder.build())
    }
}