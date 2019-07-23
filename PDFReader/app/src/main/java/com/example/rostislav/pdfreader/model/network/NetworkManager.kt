package com.example.rostislav.pdfreader.model.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okio.*

class NetworkManager : Network {

    override fun downloadFromNetwork(url: String, progressCallback: ((Long) -> Unit)?): ByteArray {
        val client = (OkHttpClient.Builder().addNetworkInterceptor {
            val original = it.proceed(it.request())
            original.newBuilder()
                .body(ProgressResponseBody(original.body!!, progressCallback))
                .build()
        }).build()
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute().body!!.bytes()
    }

    class ProgressResponseBody(
        val responseBody: ResponseBody,
        var progressCallback: ((bytesDownloaded: Long) -> Unit)?
    ) :
        ResponseBody() {

        private var bufferedSource: BufferedSource? = null

        override fun contentLength() = responseBody.contentLength()

        override fun contentType() = responseBody.contentType()

        override fun source(): BufferedSource {
            return bufferedSource ?: source(responseBody.source()).buffer()
        }

        private fun source(source: Source): Source {

            var totalBytesRead: Long = 0

            return object : ForwardingSource(source) {
                override fun read(sink: Buffer, byteCount: Long): Long {
                    val bytesRead = super.read(sink, byteCount)
                    totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                    progressCallback!!.invoke((totalBytesRead * 100).div(responseBody.contentLength()))
                    return bytesRead
                }
            }
        }
    }
}