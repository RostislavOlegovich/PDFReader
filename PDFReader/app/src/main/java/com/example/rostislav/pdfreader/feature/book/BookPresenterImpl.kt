package com.example.rostislav.pdfreader.feature.book

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter

class BookPresenterImpl(val context: Context) : BasePresenter<BookView>(context), BookPresenter {

    override fun readFromStorage(filename: String) {
        val file = fileManager.readFileFromInternalStorage(filename)
        view?.showView(file)
    }
}