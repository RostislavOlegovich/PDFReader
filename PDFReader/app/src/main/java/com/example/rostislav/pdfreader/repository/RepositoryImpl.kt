package com.example.rostislav.pdfreader.repository

import android.content.Context
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.database.DatabaseManager
import com.example.rostislav.pdfreader.model.file.FileManager
import com.example.rostislav.pdfreader.model.file.FileManagerImpl
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
import java.io.File

class RepositoryImpl(context: Context) : Repository {
    private val network: Network = NetworkManager()
    private val database: Database = DatabaseManager(context)
    private val fileManager: FileManager = FileManagerImpl(context)

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