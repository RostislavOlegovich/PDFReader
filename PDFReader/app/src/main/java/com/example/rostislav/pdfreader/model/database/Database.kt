package com.example.rostislav.pdfreader.model.database

import android.content.Context
import java.io.File

interface Database {

    fun setData(context: Context)

    fun getData(): File
}