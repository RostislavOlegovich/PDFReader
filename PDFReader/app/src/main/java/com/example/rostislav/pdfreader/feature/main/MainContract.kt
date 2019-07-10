package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.feature.mvp.MVPPresenter
import com.example.rostislav.pdfreader.feature.mvp.MVPView
import java.io.File

interface View : MVPView {
    fun showView(file: File)
}

interface Presenter : MVPPresenter<View> {
    fun downloadView(string: String, context: Context)
}