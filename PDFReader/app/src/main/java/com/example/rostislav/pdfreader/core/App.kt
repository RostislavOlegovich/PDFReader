package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.core.configuration.Configuration
import com.example.rostislav.pdfreader.core.di.ManagersInjection

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ManagersInjection.setup(this)
        Configuration.setupStetho(this)
        Configuration.setupAsync()
    }
}