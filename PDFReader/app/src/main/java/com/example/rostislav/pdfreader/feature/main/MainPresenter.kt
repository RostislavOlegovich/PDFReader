package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.model.database.room.FileData
import java.io.File

class MainPresenter(val context: Context) : BasePresenter<View>(context), Presenter {

    override fun getFromDatabase(fileData: FileData) {
        if (fileManager.isFileExist(fileData.localPath)) {
            view?.openActivity(fileData.localPath)
        } else {
            downloadView(fileData.url, fileData.fileName)
        }
    }

    override fun downloadView(url: String, filename: String) {
        doAsync(
                {
                    val response = network.downloadFromNetwork(url, context)
                    fileManager.writeFileToInternalStorage(response, filename)
                },
                { writeToDatabase(it, url) },
                this::onError
        )
    }

    override fun getAll() {
        doAsync({ database.getAll() }, { view?.showView(it) }, this::onError)
    }

    override fun readFromStorage(filePath: String): File {
        return fileManager.readFileFromInternalStorage(filePath)
    }

    override fun writeToDatabase(file: File, url: String) {
        doAsync({ database.update(FileData(url, file.absolutePath, file.name)) },
                { view?.openActivity(file.absolutePath) },
                this::onError)
    }
}