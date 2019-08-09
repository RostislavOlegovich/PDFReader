package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.rostislav.pdfreader.core.base.BaseObserver
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData

class MainPresenter(val context: Context) : BasePresenter<View>(context), Presenter {
    override val showListLD = MutableLiveData<List<FileData>>()

    override val fileDownloadedLD = MutableLiveData<String>()

    override val showThumbnailLD = MutableLiveData<FileData>()

    override val loadingProgressLD = MutableLiveData<Data>()

    private val databaseObserver = object : BaseObserver<FileData>() {
        override fun onObserve(data: FileData) {
            showThumbnailLD.value = data
        }
    }

    private val networkObserver = object : BaseObserver<Data>() {
        override fun onObserve(data: Data) {
            loadingProgressLD.value = data
        }
    }

    override fun loadAllFiles() {
        doAsync({ repository.loadAllFiles() }, { showListLD.value = it })
    }

    override fun loadFile(url: String) {
        repository.getObservableNetwork().subscribe(networkObserver)
        repository.getObservableDatabase().subscribe(databaseObserver)
        doAsync({ repository.loadFile(url) },
            { fileDownloadedLD.value = it.url })
    }
}