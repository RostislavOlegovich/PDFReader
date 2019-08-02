package com.example.rostislav.pdfreader.model.presenter

import android.util.ArrayMap
import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView

class PresenterManagerImpl : PresenterManager {
    private val presenters = ArrayMap<String, MVPPresenter<*>>()

    @Suppress("UNCHECKED_CAST")
    override fun <V : MVPView, P : MVPPresenter<V>> getPresenter(tag: String, factory: PresenterFactory<V, P>): P {
        return if (presenters[tag] != null) {
            presenters[tag] as P
        } else {
            val presenter = factory.create()
            presenters[tag] = presenter
            presenter
        }
    }

    override fun removePresenter(tag: String) {
        presenters.remove(tag)
    }
}