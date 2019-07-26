package com.example.rostislav.pdfreader.model.network

import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.Observable
import com.example.rostislav.pdfreader.entity.Observer

interface Network {
    fun getObservable(): Observable<Data, Observer<Data>>

    fun downloadFromNetwork(url: String, observer: Observer<Data>)
}