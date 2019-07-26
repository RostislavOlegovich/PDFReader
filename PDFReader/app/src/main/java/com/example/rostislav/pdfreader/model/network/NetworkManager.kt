package com.example.rostislav.pdfreader.model.network

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.rostislav.pdfreader.core.base.BaseObservable
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.Observable
import com.example.rostislav.pdfreader.entity.Observer
import com.example.rostislav.pdfreader.feature.service.NetworkService

class NetworkManager(val context: Context) : Network {
    private val observable = BaseObservable()

    override fun getObservable(): Observable<Data, Observer<Data>> {
        return observable
    }

    override fun downloadFromNetwork(url: String, observer: Observer<Data> ) {
        observable.subscribe(observer)
        val serviceIntent = Intent(context, NetworkService::class.java)
        serviceIntent.putExtra("url", url)
        ContextCompat.startForegroundService(context, serviceIntent)
    }
}