package com.example.rostislav.pdfreader.repository

import android.content.Context
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.file.FileManager
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.utils.getNameFromString
import com.example.rostislav.pdfreader.utils.system.FileNotExistException
import java.io.File

class FileRepositoryImpl(
    private val context: Context,
    private val network: Network,
    private val fileManager: FileManager,
    private val database: Database

) : FileRepository {
    private var callback: ((ByteArray) -> Unit)? = {}

    override fun loadFile(url: String): File {
        val fileData = database.getData(url)
        val file = fileManager.readFile(fileData.localPath)
        return if (file.exists()) {
            file
        } else {
            download(url)
            throw FileNotExistException(null)
        }
    }

    override fun loadAllFiles() = database.getAllData()

    override fun getObservable() = network.getObservable()

    private fun download(url: String) {
        callback = { byteArray -> write(byteArray, url) }
        network.startNetworkService(url, callback)
    }

    private fun write(byteArray: ByteArray, url: String) {
        val filename = getNameFromString(url)
        fileManager.writeFile(byteArray, filename)
        val filePath = File(context.filesDir, filename).absolutePath
        val file = fileManager.readFile(filePath)
        val thumbnail = fileManager.generateThumbnail(file)
        database.update(FileData(url, file.absolutePath, file.name, thumbnail.absolutePath))
    }
}