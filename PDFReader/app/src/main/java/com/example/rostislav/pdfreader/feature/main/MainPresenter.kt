package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data

class MainPresenter(val context: Context) : BasePresenter<View>(context), Presenter, Observer<Data> {
    override fun loadAllFiles() {
        doAsync({ repository.loadAllFiles() }, { view?.show(it) })
    }

    override fun loadFile(url: String) {
        repository.getObservable().subscribe(this)
        doAsync({ repository.loadFile(url) }, { view?.fileDownloaded(url) })
    }

    override fun onObserve(data: Data) {
        view?.loadingProgress(data.progress, data.url)
        if (data.progress == 100L) view?.showThumbnail()
    }
}