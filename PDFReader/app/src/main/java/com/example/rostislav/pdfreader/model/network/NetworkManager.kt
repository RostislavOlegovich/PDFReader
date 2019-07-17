package com.example.rostislav.pdfreader.model.network

import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkManager : Network {
    private val client = OkHttpClient()

    override fun downloadFromNetwork(url: String): ByteArray {
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute().body!!.bytes()
    }
}