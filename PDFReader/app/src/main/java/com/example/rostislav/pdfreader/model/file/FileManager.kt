package com.example.rostislav.pdfreader.model.file

import com.example.rostislav.pdfreader.core.base.BaseManager
import java.io.File

interface FileManager : BaseManager {
    fun writeFile(byteArray: ByteArray, filename: String)

    fun readFile(localPath: String): File

    fun generateThumbnail(file: File): File
}