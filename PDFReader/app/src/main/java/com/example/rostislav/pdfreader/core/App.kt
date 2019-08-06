package com.example.rostislav.pdfreader.core

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppInjector.setup(this)
    }
}