package com.example.rostislav.pdfreader.model.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class NetworkManager : Network {
    private val client = OkHttpClient()

    override fun downloadFromNetwork(url: String, context: Context): Response {
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute()
    }
}