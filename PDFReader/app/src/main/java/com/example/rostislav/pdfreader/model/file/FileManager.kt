package com.example.rostislav.pdfreader.model.file

import android.content.Context
import okhttp3.Response
import java.io.File

interface FileManager {
    fun writeFileToInternalStorage(context: Context, response: Response, filename: String)

    fun readFileFromInternalStorage(filename: String, context: Context): File
}