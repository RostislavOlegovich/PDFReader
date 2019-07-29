package com.example.rostislav.pdfreader.core.observer

interface Observer<D> {

    fun onObserve(data: D)

    fun onError(exception: Throwable)
}