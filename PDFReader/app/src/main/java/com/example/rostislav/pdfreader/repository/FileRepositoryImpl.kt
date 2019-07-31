package com.example.rostislav.pdfreader.repository

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BaseFileRepository
import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData
import org.jetbrains.anko.doAsync
import java.io.File

class FileRepositoryImpl(private val context: Context) : FileRepository, BaseFileRepository(context) {

    override fun read(localPath: String): File {
        return fileManager.readFile(localPath)
    }

    override fun write(byteArray: ByteArray, filename: String, url: String) {
        fileManager.writeFile(byteArray, filename)
        val filePath = File(context.filesDir, filename).absolutePath
        val file = fileManager.readFile(filePath)
        val thumbnail = generateThumbnail(file)
        doAsync {
            update(FileData(url, file.absolutePath, file.name, thumbnail.absolutePath))
        }
    }

    override fun download(url: String) {
        network.startNetworkService(url)
    }

    override fun getAllData() = database.getAllData()

    override fun getObservable(): Observable<Data, Observer<Data>> {
        return network.getObservable()
    }

    private fun generateThumbnail(file: File) = fileManager.generateThumbnail(file)

    private fun update(fileData: FileData) {
        database.update(fileData)
    }

    private fun getData(key: String) = database.getData(key)
}