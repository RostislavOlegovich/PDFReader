package com.example.rostislav.pdfreader.model.network

import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data

interface Network {
    fun startNetworkService(url: String)

    fun downloadFromNetwork(url: String)

    fun getObservable(): Observable<Data, Observer<Data>>
}