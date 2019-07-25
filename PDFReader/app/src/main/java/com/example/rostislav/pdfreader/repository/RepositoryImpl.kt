package com.example.rostislav.pdfreader.repository

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BaseRepository
import com.example.rostislav.pdfreader.entity.FileData
import java.io.File

class RepositoryImpl(context: Context) : BaseRepository(context), Repository {

    override fun write(byteArray: ByteArray, filename: String) = fileManager.writeFile(byteArray, filename)

    override fun read(localPath: String) = fileManager.readFile(localPath)

    override fun isFileExist(localPath: String) = fileManager.isFileExist(localPath)

    override fun generateImageFromPdf(file: File) = fileManager.generateImageFromPdf(file)

    override fun downloadFromNetwork(url: String, progressCallback: ((Long) -> Unit)?): ByteArray {
        return network.downloadFromNetwork(url, progressCallback)
    }

    override fun update(fileData: FileData) {
        database.update(fileData)
    }

    override fun getData(key: String) = database.getData(key)

    override fun getAllData() = database.getAllData()
}