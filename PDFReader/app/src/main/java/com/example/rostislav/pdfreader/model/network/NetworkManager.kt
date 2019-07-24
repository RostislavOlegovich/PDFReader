package com.example.rostislav.pdfreader.model.network

import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkManager : Network {

    override fun downloadFromNetwork(url: String, progressCallback: ((Long) -> Unit)?): ByteArray {
        val client = (OkHttpClient.Builder().addNetworkInterceptor {
            val originalResponse = it.proceed(it.request())
            originalResponse.newBuilder()
                .body(ProgressResponseBody(originalResponse.body!!, progressCallback))
                .build()
        }).build()
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute().body!!.bytes()
    }
}