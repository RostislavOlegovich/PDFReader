package com.example.rostislav.pdfreader.model.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkManager : Network {
    private val client = OkHttpClient()
    private val filename = "file"

    override fun connectToNetwork(url: String, context: Context) {
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        context.openFileOutput(filename, Context.MODE_PRIVATE)
            .use { it.write(response.body?.bytes()) }
    }
}