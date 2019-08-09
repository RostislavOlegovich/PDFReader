package com.example.rostislav.pdfreader.core.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

abstract class ServiceLifecycleObserver : BaseService(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    abstract fun showNotification()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    abstract fun hideNotification()
}