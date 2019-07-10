package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter

class MainPresenter(context: Context) : BasePresenter<View>(context), Presenter {

    override fun downloadView(string: String, context: Context) {
        doAsync(
            { network.downloadFromNetwork(string, context) },
            { view?.showView(network.getFile(context)) }
        )
    }
}