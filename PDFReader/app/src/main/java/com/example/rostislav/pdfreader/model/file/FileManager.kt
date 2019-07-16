package com.example.rostislav.pdfreader.model.file

import okhttp3.Response
import java.io.File

interface FileManager {
    fun writeFileToInternalStorage(response: Response, filename: String) : File

    fun readFileFromInternalStorage(localPath: String): File

    fun isFileExist(localPath: String): Boolean
}