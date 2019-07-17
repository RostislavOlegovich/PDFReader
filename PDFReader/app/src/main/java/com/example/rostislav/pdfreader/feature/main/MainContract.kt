package com.example.rostislav.pdfreader.feature.main

import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView
import com.example.rostislav.pdfreader.model.database.room.FileData

interface View : MVPView {
    fun showView(data: List<FileData>)

    fun openActivity(data: String)
}

interface Presenter : MVPPresenter<View> {
    fun loadFile(fileData: FileData)

    fun loadAllFiles()
}