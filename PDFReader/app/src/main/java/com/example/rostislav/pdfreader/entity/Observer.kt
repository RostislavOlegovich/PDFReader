package com.example.rostislav.pdfreader.entity

interface Observer<D> {

    fun onObserve(data: D)

    fun onError(exception: Throwable)
}