package com.example.rostislav.pdfreader.feature.book

import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView
import java.io.File

interface BookView : MVPView {
    fun show(data: File)
}

interface BookPresenter : MVPPresenter<BookView> {
    fun readFromStorage(localPath: String)
}