package com.example.rostislav.pdfreader.feature.activity.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.utils.getNameFromString

class MainPresenter(context: Context) : BasePresenter<View>(context), Presenter, Observer<Data> {

    override fun loadFile(fileData: FileData) {
        if (repository.isFileExist(fileData.localPath)) {
            view?.fileDownloaded(fileData.localPath)
        } else {
            download(fileData.url, this)
        }
    }

    override fun loadAllFiles() {
        doAsync({ repository.getAllData() },
            { view?.show(it) })
    }

    override fun onObserve(data: Data) {
        handler.post {
            view?.loadingProgress(data.progress, data.url)
        }
        data.file?.let {
            val filePath = repository.write(data.file, getNameFromString(data.url)).absolutePath
            writeToDatabase(filePath, data.url)
        }
    }

    private fun download(url: String, observer: Observer<Data>) {
        repository.downloadFromNetwork(url, observer)
    }

    private fun writeToDatabase(filepath: String, url: String) {
        doAsync(
            {
                if (repository.isFileExist(filepath)) {
                    val file = repository.read(filepath)
                    val thumbnail = repository.generateThumbnail(file)
                    repository.update(FileData(url, file.absolutePath, file.name, thumbnail.absolutePath))
                }
            },
            {
                view?.fileDownloaded(filepath)
            }
        )
    }
}