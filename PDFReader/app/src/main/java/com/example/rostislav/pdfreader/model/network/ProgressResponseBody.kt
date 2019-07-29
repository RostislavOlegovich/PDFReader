package com.example.rostislav.pdfreader.model.network

import okhttp3.ResponseBody
import okio.*

class ProgressResponseBody(val responseBody: ResponseBody, val progressCallback: ((Long) -> Unit)?) :
    ResponseBody() {
    private val bufferedSource: BufferedSource? = null

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