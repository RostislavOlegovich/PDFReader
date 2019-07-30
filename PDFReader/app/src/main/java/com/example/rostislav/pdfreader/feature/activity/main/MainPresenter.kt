package com.example.rostislav.pdfreader.feature.activity.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.utils.getNameFromString
import java.io.File

class MainPresenter(val context: Context) : BasePresenter<View>(context), Presenter, Observer<Data> {
    override fun loadFile(fileData: FileData) {
        if (repository.isFileExist(fileData.localPath)) {
            view?.fileDownloaded(fileData.localPath)
        } else {
            download(fileData.url, this)
        }
    }

    override fun loadAllFiles() {
        doAsync({ repository.getAllData() }, { view?.show(it) })
    }

    override fun onObserve(data: Data) {
        view?.loadingProgress(data.progress, data.url)
        data.file?.let {
            val filename = getNameFromString(data.url)
            repository.write(it, filename)
            val filePath = File(context.filesDir, filename).absolutePath
            writeToDatabase(filePath, data.url)
        }
    }

    private fun download(url: String, observer: Observer<Data>) {
        repository.downloadFromNetwork(url, observer)
    }

    private fun writeToDatabase(filepath: String, url: String) {
        val file = repository.read(filepath)
        val thumbnail = repository.generateThumbnail(file)
        doAsync(
            {
                repository.update(FileData(url, file.absolutePath, file.name, thumbnail.absolutePath))
            },
            {
                view?.showThumbnail()
            }
        )
    }
}