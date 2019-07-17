package com.example.rostislav.pdfreader.feature.book

import com.example.rostislav.pdfreader.feature.mvp.MVPPresenter
import com.example.rostislav.pdfreader.feature.mvp.MVPView
import java.io.File

interface BookView : MVPView {
    fun showView(data: File)
}

interface BookPresenter : MVPPresenter<BookView> {
    fun readFromStorage(localPath: String)
}