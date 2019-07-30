package com.example.rostislav.pdfreader.repository

import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData
import java.io.File

interface Repository {
    fun write(byteArray: ByteArray, filename: String)

    fun read(localPath: String): File

    fun isFileExist(localPath: String): Boolean

    fun downloadFromNetwork(url: String, observer: Observer<Data>)

    fun update(fileData: FileData)

    fun getData(key: String): FileData

    fun getAllData(): List<FileData>

    fun generateThumbnail(file: File): File
}