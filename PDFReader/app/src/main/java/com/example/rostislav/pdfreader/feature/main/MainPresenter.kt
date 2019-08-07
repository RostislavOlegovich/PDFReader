package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData

class MainPresenter(val context: Context) : BasePresenter<View>(context), Presenter, Observer<Data> {
    override val showListLD = MutableLiveData<List<FileData>>()

    override val fileDownloadedLD = MutableLiveData<String>()

    override val showThumbnailLD = MutableLiveData<Unit>()

    override val loadingProgressLD = MutableLiveData<Data>()

    override fun loadAllFiles() {
        doAsync({ repository.loadAllFiles() }, { showListLD.postValue(it) })
    }

    override fun loadFile(url: String) {
        repository.getObservable().subscribe(this)
        doAsync({ repository.loadFile(url) }, { fileDownloadedLD.postValue(url) })
    }

    override fun onObserve(data: Data) {
        loadingProgressLD.postValue(data)
        if (data.progress == 100L) showThumbnailLD.postValue(Unit)
    }
}