package com.example.rostislav.pdfreader.model.file

import java.io.File

interface FileManager {
    fun writeFile(byteArray: ByteArray, filename: String)

    fun readFile(localPath: String): File

    fun isFileExist(localPath: String): Boolean

    fun generateThumbnail(file: File): File
}