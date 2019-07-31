package com.example.rostislav.pdfreader.feature.activity.main

import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView
import com.example.rostislav.pdfreader.entity.FileData

interface View : MVPView {
    fun show(data: List<FileData>)

    fun fileDownloaded(data: String)

    fun showThumbnail()

    fun loadingProgress(progress: Long, url: String)
}

interface Presenter : MVPPresenter<View> {
    fun loadAllFiles()

    fun loadFile(fileData: FileData)
}