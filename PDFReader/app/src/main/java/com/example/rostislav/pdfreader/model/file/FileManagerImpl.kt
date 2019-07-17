package com.example.rostislav.pdfreader.model.file

import android.content.Context
import java.io.File

class FileManagerImpl(val context: Context) : FileManager {

    override fun writeFile(byteArray: ByteArray, filename: String): File {
        context.openFileOutput(filename, Context.MODE_PRIVATE)
            .use { it.write(byteArray) }
        return File(context.filesDir, filename)
    }

    override fun readFile(localPath: String) = File(localPath)

    override fun isFileExist(localPath: String) = File(localPath).exists()
}