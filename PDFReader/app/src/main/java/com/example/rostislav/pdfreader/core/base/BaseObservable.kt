package com.example.rostislav.pdfreader.core.base

import android.os.Looper
import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data

open class BaseObservable : Observable<Data, Observer<Data>> {
    private val handler = android.os.Handler(Looper.getMainLooper())
    private val observers = mutableListOf<Observer<Data>>()

    override fun subscribe(observer: Observer<Data>) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: Observer<Data>) {
        observers.remove(observer)
    }

    override fun isSubscribed(observer: Observer<Data>) = observers.contains(observer)

    override fun notifyObserversChange(data: Data) {
        handler.post {
            observers.forEach { it.onObserve(data) }
        }
    }

    override fun notifyObserversError(exception: Throwable) {
        handler.post {
            observers.forEach { it.onError(exception) }
        }
    }
}