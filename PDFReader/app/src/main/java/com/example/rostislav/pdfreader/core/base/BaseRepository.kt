package com.example.rostislav.pdfreader.core.base

import android.content.Context
import com.example.rostislav.pdfreader.core.App

abstract class BaseRepository(context: Context) {
    val network = (context as App).network
    val database = (context as App).database
    val fileManager = (context as App).fileManager
}