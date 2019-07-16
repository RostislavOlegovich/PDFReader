package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.feature.mvp.MVPPresenter
import com.example.rostislav.pdfreader.feature.mvp.MVPView
import com.example.rostislav.pdfreader.model.database.room.FileData
import okhttp3.Response
import java.io.File

interface View : MVPView {
    fun showView(data: MutableList<FileData>)

    fun openActivity(data: String)
}

interface Presenter : MVPPresenter<View> {
    fun downloadView(url: String, context: Context, filename: String)

    fun getFromDatabase(key: String, context: Context)

    fun getAll()

    fun readFromStorage(filename: String, context: Context): File

    fun writeToStorage(response: Response, context: Context, filename: String)
}