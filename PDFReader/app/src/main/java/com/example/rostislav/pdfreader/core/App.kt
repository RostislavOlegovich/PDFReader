package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.core.configuration.Configuration
import com.example.rostislav.pdfreader.core.di.ManagersInjection
import com.example.rostislav.pdfreader.core.di.RepositoryInjection

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ManagersInjection.setup(this)
        RepositoryInjection.provideRepository(this)
        Configuration.setup(this)
    }
}