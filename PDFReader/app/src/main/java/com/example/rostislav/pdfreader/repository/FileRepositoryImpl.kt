package com.example.rostislav.pdfreader.repository

import android.content.Context
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.entity.exception.FileNotExistException
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.file.FileManager
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.utils.StringUtils
import java.io.File

class FileRepositoryImpl(
    private val context: Context,
    private val network: Network,
    private val fileManager: FileManager,
    private val database: Database

) : FileRepository {

    override fun loadFile(url: String): FileData {
        val fileData = database.getData(url)
        val file = fileManager.readFile(fileData.localPath)
        return if (file.exists()) {
            fileData
        } else {
            download(url)
            throw FileNotExistException(url)
        }
    }

    override fun loadAllFiles() = database.getAllData()

    override fun getObservableNetwork() = network.getObservable()

    override fun getObservableDatabase() = database.getObservable()

    private fun download(url: String) {
        network.startNetworkService(url) { byteArray, urla -> write(byteArray, urla) }
    }

    private fun write(byteArray: ByteArray, url: String) {
        val filename = StringUtils.getNameFromString(url)
        fileManager.writeFile(byteArray, filename)
        val filePath = File(context.filesDir, filename).absolutePath
        val file = fileManager.readFile(filePath)
        val thumbnail = fileManager.generateThumbnail(file)
        database.update(FileData(url, file.absolutePath, file.name, thumbnail.absolutePath))
    }
}