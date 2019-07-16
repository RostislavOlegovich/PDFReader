package com.example.rostislav.pdfreader.model.file

import android.content.Context
import okhttp3.Response
import java.io.File

class FileManagerImpl(val context: Context) : FileManager {

    override fun writeFileToInternalStorage(response: Response, filename: String): File {
        context.openFileOutput(filename, Context.MODE_PRIVATE)
                .use { it.write(response.body?.bytes()!!) }
        return File(context.filesDir,filename)
    }

    override fun readFileFromInternalStorage(localPath: String) = File(localPath)

    override fun isFileExist(localPath: String) = File(localPath).exists()
}