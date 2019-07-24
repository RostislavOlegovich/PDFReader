package com.example.rostislav.pdfreader.feature.book

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter

class BookPresenterImpl(context: Context) : BasePresenter<BookView>(context), BookPresenter {

    override fun readFromStorage(localPath: String) {
        view?.show(repository.read(localPath))
    }
}