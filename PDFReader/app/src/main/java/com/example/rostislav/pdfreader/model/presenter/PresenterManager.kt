package com.example.rostislav.pdfreader.model.presenter

import com.example.rostislav.pdfreader.core.interfaces.Manager
import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView

interface PresenterManager : Manager {
    fun <V : MVPView, P : MVPPresenter<V>> getPresenter(tag: String, factory: PresenterFactory<V, P>): P

    fun removePresenter(tag: String)
}