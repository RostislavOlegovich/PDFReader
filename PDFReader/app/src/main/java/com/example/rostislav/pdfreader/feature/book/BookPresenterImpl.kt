package com.example.rostislav.pdfreader.feature.book

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter

class BookPresenterImpl(val context: Context) : BasePresenter<BookView>(context), BookPresenter {

    override fun readFromStorage(localPath: String) {
        val file = fileManager.readFile(localPath)
        view?.showView(file)
    }
}