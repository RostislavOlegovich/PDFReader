package com.example.rostislav.pdfreader.model.network

import android.content.Context
import okhttp3.Response

interface Network {
    fun downloadFromNetwork(url: String, context: Context): Response
}