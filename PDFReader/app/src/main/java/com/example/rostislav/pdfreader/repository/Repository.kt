package com.example.rostislav.pdfreader.repository

import com.example.rostislav.pdfreader.entity.FileData
import java.io.File

interface Repository {
    fun write(byteArray: ByteArray, filename: String): File

    fun read(localPath: String): File

    fun isFileExist(localPath: String): Boolean

    fun downloadFromNetwork(url: String, progressCallback: ((Long) -> Unit)?): ByteArray

    fun update(fileData: FileData)

    fun getData(key: String): FileData

    fun getAllData(): List<FileData>

    fun generateImageFromPdf(file: File): File
}