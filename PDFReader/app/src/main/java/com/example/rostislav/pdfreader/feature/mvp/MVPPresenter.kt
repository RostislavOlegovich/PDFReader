package com.example.rostislav.pdfreader.feature.mvp

interface MVPPresenter<V : MVPView> {
    var view: V?

    fun attach(view: V) {
        this.view = view
    }

    fun dettach() {
        this.view = null
    }

    fun isAttach() = this.view != null
}