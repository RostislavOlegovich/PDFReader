package com.example.rostislav.pdfreader.feature.main

import androidx.lifecycle.LiveData
import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData

interface View : MVPView

interface Presenter : MVPPresenter<View> {
    val showListLD: LiveData<List<FileData>>

    val fileDownloadedLD: LiveData<String>

    val showThumbnailLD: LiveData<Unit>

    val loadingProgressLD: LiveData<Data>

    fun loadAllFiles()

    fun loadFile(url: String)
}