package com.example.rostislav.pdfreader.core

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.rostislav.pdfreader.core.configuration.Configuration
import com.example.rostislav.pdfreader.core.di.ConcurrencyInjection
import com.example.rostislav.pdfreader.core.di.ManagersInjection
import com.example.rostislav.pdfreader.utils.system.LifecycleListener

object AppInjector {
    fun setup(context: Context) {
        ManagersInjection.setup(context)
        ConcurrencyInjection.setup()
        Configuration.setupStetho(context as Application)
        ProcessLifecycleOwner.get().lifecycle.addObserver(LifecycleListener)
    }
}