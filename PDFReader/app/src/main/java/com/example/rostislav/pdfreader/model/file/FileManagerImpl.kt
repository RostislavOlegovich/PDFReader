package com.example.rostislav.pdfreader.model.file

import android.content.Context
import okhttp3.Response
import java.io.File

class FileManagerImpl : FileManager {

    override fun writeFileToInternalStorage(context: Context, response: Response, filename: String) {
        context.openFileOutput(filename, Context.MODE_PRIVATE)
                .use { it.write(response.body?.bytes()!!) }
    }

    override fun readFileFromInternalStorage(filename: String, context: Context)
            = File(context.filesDir, filename)
}