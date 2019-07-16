package com.example.rostislav.pdfreader.feature.second

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter

class BookPresenterImpl(context: Context) : BasePresenter<BookView>(context), BookPresenter {

    override fun readFromStorage(filename: String, context: Context) {
        val file = fileManager.readFileFromInternalStorage(filename, context)
        view?.showView(file)
    }
}