package com.example.rostislav.pdfreader.core.observer

interface Observable<D, in O : Observer<D>> {
    fun subscribe(observer: O)

    fun unsubscribe(observer: O)

    fun isSubscribed(observer: O): Boolean

    fun notifyObserversChange(data: D)

    fun notifyObserversError(exception: Throwable)
}