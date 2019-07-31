package com.example.rostislav.pdfreader.feature.activity.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.utils.getNameFromString

class MainPresenter(context: Context) : BasePresenter<View>(context), Presenter, Observer<Data> {
    override fun loadAllFiles() {
        doAsync({ repository.getAllData() }, { view?.show(it) })
        repository.getObservable().subscribe(this)
    }

    override fun loadFile(fileData: FileData) {
        if (repository.read(fileData.localPath).exists()) {
            view?.fileDownloaded(fileData.localPath)
        } else {
            repository.download(fileData.url)
        }
    }

    override fun onObserve(data: Data) {
        view?.loadingProgress(data.progress, data.url)
        data.byteArray?.let {
            val filename = getNameFromString(data.url)
            repository.write(it, filename, data.url)
            view?.showThumbnail()
        }
    }
}