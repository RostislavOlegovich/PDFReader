package com.example.rostislav.pdfreader.model.network

import android.content.Context

interface Network {
    fun connectToNetwork(url: String, context: Context)
}