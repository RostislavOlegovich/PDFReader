package com.example.rostislav.pdfreader.model.network

import android.content.Context
import java.io.File

interface Network {
    fun downloadFromNetwork(url: String, context: Context)

    fun getFile(context: Context): File
}