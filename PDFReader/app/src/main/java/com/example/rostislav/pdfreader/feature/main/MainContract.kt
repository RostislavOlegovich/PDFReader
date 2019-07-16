package com.example.rostislav.pdfreader.feature.main

import com.example.rostislav.pdfreader.feature.mvp.MVPPresenter
import com.example.rostislav.pdfreader.feature.mvp.MVPView
import com.example.rostislav.pdfreader.model.database.room.FileData
import java.io.File

interface View : MVPView {
    fun showView(data: MutableList<FileData>)

    fun openActivity(data: String)
}

interface Presenter : MVPPresenter<View> {
    fun downloadView(url: String, filename: String)

    fun getFromDatabase(fileData: FileData)

    fun getAll()

    fun readFromStorage(filePath: String): File

    fun writeToDatabase(file: File, url: String)
}