package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.model.database.room.FileData
import java.io.File

class MainPresenter(val context: Context) : BasePresenter<View>(context), Presenter {

    override fun loadFile(fileData: FileData) {
        if (fileManager.isFileExist(fileData.localPath)) {
            view?.openActivity(fileData.localPath)
        } else {
            download(fileData.url, fileData.fileName)
        }
    }

    override fun loadAllFiles() {
        doAsync(
            { database.getAllData() }, { view?.showView(it) }, this::onError
        )
    }

    private fun download(url: String, filename: String) {
        doAsync(
            {
                val response = network.downloadFromNetwork(url)
                fileManager.writeFile(response, filename)
            },
            {
                writeToDatabase(it, url)
            },
            this::onError
        )
    }

    private fun writeToDatabase(file: File, url: String) {
        doAsync(
            {
                val thumbnail = fileManager.generateImageFromPdf(file)
                database.update(FileData(url, file.absolutePath, file.name, thumbnail.absolutePath))
            },
            {
                view?.openActivity(file.absolutePath)
            },
            this::onError
        )
    }
}