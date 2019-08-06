package com.example.rostislav.pdfreader.model.network

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BaseObservable
import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.model.network.service.NetworkService
import com.example.rostislav.pdfreader.utils.StringUtils.getExtraStringIntent
import com.example.rostislav.pdfreader.utils.extension.createIntent
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkManager(private val context: Context) : Network, Observable<Data, Observer<Data>> by BaseObservable() {
    private val list = mutableListOf<String>()

    private var callback: ((ByteArray) -> Unit)? = null

    override fun showNotification() {
        if (list.isNotEmpty()) {
            context.startService(
                context.createIntent(
                    NetworkService::class.java, ACTION_SHOW_NOTIFICATION
                )
            )
        }
    }

    override fun hideNotification() {
        if (list.isNotEmpty()) {
            context.startService(
                context.createIntent(
                    NetworkService::class.java, ACTION_HIDE_NOTIFICATION
                )
            )
        }
    }

    override fun startNetworkService(url: String, callBack: ((ByteArray) -> Unit)?) {
        list.add(url)
        callback = callBack
        val serviceIntent = context.createIntent(NetworkService::class.java, ACTION_START_FOREGROUND) {
            putString(getExtraStringIntent(), url)
        }
        context.startService(serviceIntent)
    }

    override fun downloadFromNetwork(url: String): ByteArray {
        val client = (OkHttpClient.Builder().addNetworkInterceptor {
            val originalResponse = it.proceed(it.request())
            originalResponse.newBuilder()
                .body(ProgressResponseBody(originalResponse.body!!)
                { progress -> notifyObserversChange(Data(progress, url)) })
                .build()
        }).build()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body!!.bytes()
    }

    override fun returnBytesArray(bytes: ByteArray) {
        list.removeAt(INDEX)
        callback?.invoke(bytes)
        context.startService(context.createIntent(NetworkService::class.java, ACTION_STOP_SERVICE))
    }

    override fun getObservable() = this as Observable<Data, Observer<Data>>

    companion object {
        private const val ACTION_START_FOREGROUND = "start_service"
        private const val ACTION_STOP_SERVICE = "stop_service"
        private const val ACTION_HIDE_NOTIFICATION = "hide"
        private const val ACTION_SHOW_NOTIFICATION = "show"
        private const val INDEX = 0
    }
}