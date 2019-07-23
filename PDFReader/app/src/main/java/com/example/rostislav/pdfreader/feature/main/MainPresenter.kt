package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.entity.FileData

class MainPresenter(val context: Context) : BasePresenter<View>(context), Presenter {

    override fun loadFile(fileData: FileData) {
        if (fileManager.isFileExist(fileData.localPath)) {
            view?.fileDownloaded(fileData.localPath)
        } else {
            download(fileData.url, fileData.fileName)
        }
    }

    override fun loadAllFiles() {
        doAsync(
            { database.getAllData() }, { view?.show(it) }
        )
    }

    private fun download(url: String, filename: String) {
        doAsync(
            {
                val response = network.downloadFromNetwork(url)
                { bytesDownloaded ->
                    handler.post {
                        view?.loadingProgress(bytesDownloaded, url)
                    }
                }
                fileManager.writeFile(response, filename).absolutePath
            },
            {
                writeToDatabase(it, url)
            }
        )
    }

    private fun writeToDatabase(filepath: String, url: String) {
        doAsync(
            {
                if (fileManager.isFileExist(filepath)) {
                    val file = fileManager.readFile(filepath)
                    val thumbnail = fileManager.generateImageFromPdf(file)
                    database.update(FileData(url, file.absolutePath, file.name, thumbnail.absolutePath))
                }
            },
            {
                view?.fileDownloaded(filepath)
            }
        )
    }
}