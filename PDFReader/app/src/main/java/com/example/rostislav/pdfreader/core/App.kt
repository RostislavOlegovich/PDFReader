package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.core.configuration.Configuration

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppInjector.setup(this)
        Configuration.setupStetho(this)
    }
}