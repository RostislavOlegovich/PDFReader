package com.example.rostislav.pdfreader.utils.system

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.rostislav.pdfreader.core.di.ManagersInjection
import com.example.rostislav.pdfreader.model.network.Network

object LifecycleListener : LifecycleObserver {
    private val network: Network = ManagersInjection.get()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun showNotification() {
        network.showNotification()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun hideNotification() {
        network.hideNotification()
    }
}