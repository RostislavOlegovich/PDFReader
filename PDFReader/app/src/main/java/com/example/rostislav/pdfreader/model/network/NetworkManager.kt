package com.example.rostislav.pdfreader.model.network

import android.content.Context
import com.example.rostislav.pdfreader.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File

class NetworkManager : Network {
    private val client = OkHttpClient()

    override fun downloadFromNetwork(url: String, context: Context) {
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        writeToStorage(response, context)
    }

    override fun getFile(context: Context) = File(context.filesDir, BuildConfig.FILE_NAME)

    private fun writeToStorage(response: Response, context: Context) {
        context.openFileOutput(BuildConfig.FILE_NAME, Context.MODE_PRIVATE)
            .use { it.write(response.body?.bytes()) }
    }
}