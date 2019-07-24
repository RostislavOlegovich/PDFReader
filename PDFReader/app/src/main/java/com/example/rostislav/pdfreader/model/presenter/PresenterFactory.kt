package com.example.rostislav.pdfreader.model.presenter

import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView

interface PresenterFactory<V : MVPView, P : MVPPresenter<V>> {
    fun create(): P
}