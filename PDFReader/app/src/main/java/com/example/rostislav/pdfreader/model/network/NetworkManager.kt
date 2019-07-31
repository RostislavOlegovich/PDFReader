package com.example.rostislav.pdfreader.model.network

import android.content.Context
import android.content.Intent
import com.example.rostislav.pdfreader.core.base.BaseObservable
import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.feature.service.NetworkService
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkManager(private val context: Context) : Network, Observable<Data, Observer<Data>> by BaseObservable() {
    override fun startNetworkService(url: String) {
        val serviceIntent = Intent(context, NetworkService::class.java)
        serviceIntent.putExtra("url", url)
        context.startService(serviceIntent)
    }

    override fun downloadFromNetwork(url: String) {
        val client = (OkHttpClient.Builder().addNetworkInterceptor {
            val originalResponse = it.proceed(it.request())
            originalResponse.newBuilder()
                .body(ProgressResponseBody(originalResponse.body!!)
                { progress ->
                    notifyObserversChange(Data(progress, url, null))
                })
                .build()
        }).build()
        val request = Request.Builder()
            .url(url)
            .build()
        val byteArray = client.newCall(request).execute().body!!.bytes()
        notifyObserversChange(Data(100, url, byteArray))
    }

    override fun getObservable() = this as Observable<Data, Observer<Data>>
}