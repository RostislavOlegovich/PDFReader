package com.example.rostislav.pdfreader.model.network

import android.content.Context
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkManager : Network {

    override fun connectToNetwork(url: String, context: Context) {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {

                val filename = "file"

                context.openFileOutput(filename, Context.MODE_PRIVATE)
                    .use { it.write(response.body?.bytes()) }
            }

        })

    }

}