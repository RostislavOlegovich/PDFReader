package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.entity.FileData

class MainPresenter(context: Context) : BasePresenter<View>(context), Presenter {

    override fun loadFile(fileData: FileData) {
        if (repository.isFileExist(fileData.localPath)) {
            view?.fileDownloaded(fileData.localPath)
        } else {
            download(fileData.url, fileData.fileName)
        }
    }

    override fun loadAllFiles() {
        doAsync(
            { repository.getAllData() }, { view?.show(it) }
        )
    }

    private fun download(url: String, filename: String) {
        doAsync(
            {
                val response = repository.downloadFromNetwork(url)
                { bytesDownloaded ->
                    handler.post {
                        view?.loadingProgress(bytesDownloaded, url)
                    }
                }
                repository.write(response, filename).absolutePath
            },
            {
                writeToDatabase(it, url)
            }
        )
    }

    private fun writeToDatabase(filepath: String, url: String) {
        doAsync(
            {
                if (repository.isFileExist(filepath)) {
                    val file = repository.read(filepath)
                    val thumbnail = repository.generateImageFromPdf(file)
                    repository.update(FileData(url, file.absolutePath, file.name, thumbnail.absolutePath))
                }
            },
            {
                view?.fileDownloaded(filepath)
            }
        )
    }
}