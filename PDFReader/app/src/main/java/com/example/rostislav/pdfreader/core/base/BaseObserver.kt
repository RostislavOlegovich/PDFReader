package com.example.rostislav.pdfreader.core.base

import com.example.rostislav.pdfreader.core.observer.Observer

open class BaseObserver<T> : Observer<T> {
    override fun onObserve(data: T) {
    }

    override fun onError(exception: Throwable) {
    }
}