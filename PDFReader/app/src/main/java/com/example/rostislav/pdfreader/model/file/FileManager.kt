package com.example.rostislav.pdfreader.model.file

import com.example.rostislav.pdfreader.core.interfaces.Manager
import java.io.File

interface FileManager : Manager {
    fun writeFile(byteArray: ByteArray, filename: String)

    fun readFile(localPath: String): File

    fun generateThumbnail(file: File): File
}