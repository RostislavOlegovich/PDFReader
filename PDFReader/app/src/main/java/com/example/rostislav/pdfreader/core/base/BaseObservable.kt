package com.example.rostislav.pdfreader.core.base

import android.os.Looper
import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer

open class BaseObservable<T> : Observable<T, Observer<T>> {
    private val handler = android.os.Handler(Looper.getMainLooper())
    private val observers = mutableListOf<Observer<T>>()

    override fun subscribe(observer: Observer<T>) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: Observer<T>) {
        observers.remove(observer)
    }

    override fun isSubscribed(observer: Observer<T>) = observers.contains(observer)

    override fun notifyObserversChange(data: T) {
        handler.post { observers.forEach { it.onObserve(data) } }
    }

    override fun notifyObserversError(exception: Throwable) {
        handler.post { observers.forEach { it.onError(exception) } }
    }
}