package com.example.rostislav.pdfreader.repository

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BaseRepository
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.entity.Observable
import com.example.rostislav.pdfreader.entity.Observer
import java.io.File

class RepositoryImpl(context: Context) : Repository, BaseRepository(context) {

    override fun write(byteArray: ByteArray, filename: String) = fileManager.writeFile(byteArray, filename)

    override fun read(localPath: String) = fileManager.readFile(localPath)

    override fun isFileExist(localPath: String) = fileManager.isFileExist(localPath)

    override fun generateImageFromPdf(file: File) = fileManager.generateImageFromPdf(file)

    override fun downloadFromNetwork(url: String, observer: Observer<Data>) {
        return network.downloadFromNetwork(url,observer)
    }

    override fun update(fileData: FileData) {
        database.update(fileData)
    }

    override fun getData(key: String) = database.getData(key)

    override fun getAllData() = database.getAllData()
}