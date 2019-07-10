package com.example.rostislav.pdfreader.feature.mvp

interface MVPPresenter<V : MVPView> {
    var view: V?

    fun attach(view: V)

    fun dettach()

    fun isAttach(): Boolean
}