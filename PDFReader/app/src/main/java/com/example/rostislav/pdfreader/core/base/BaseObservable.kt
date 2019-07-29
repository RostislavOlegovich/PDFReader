package com.example.rostislav.pdfreader.core.base

import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data

class BaseObservable : Observable<Data, Observer<Data>> {
    val observers = mutableListOf<Observer<Data>>()

    override fun subscribe(observer: Observer<Data>) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: Observer<Data>) {
        observers.remove(observer)
    }

    override fun isSubscribed(observer: Observer<Data>) = observers.contains(observer)

    override fun notifyObserversChange(data: Data) {
        observers.forEach { it.onObserve(data) }
    }

    override fun notifyObserversError(exception: Throwable) {
        observers.forEach { it.onError(exception) }
    }
}