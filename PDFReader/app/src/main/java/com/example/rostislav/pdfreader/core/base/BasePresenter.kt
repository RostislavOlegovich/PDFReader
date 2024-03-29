package com.example.rostislav.pdfreader.core.base

import android.content.Context
import com.example.rostislav.pdfreader.core.di.ConcurrencyInjection
import com.example.rostislav.pdfreader.core.di.RepositoryInjection
import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView
import com.example.rostislav.pdfreader.repository.FileRepository

abstract class BasePresenter<V : MVPView>(context: Context) : MVPPresenter<V> {
    val repository = RepositoryInjection.create<FileRepository>(context)
    val executors = ConcurrencyInjection.executors
    val handler = ConcurrencyInjection.handler

    override var view: V? = null

    override fun attach(view: V) {
        this.view = view
    }

    override fun dettach() {
        this.view = null
    }

    override fun isAttach() = this.view != null

    fun onError(exception: Throwable) {
        view?.error(exception)
    }

    inline fun <T> doAsync(
        crossinline requestData: () -> T,
        crossinline useData: (T) -> Unit
//        crossinline onError: (Throwable) -> Unit = this::onError
    ) {
        executors.execute {
            try {
                val result = requestData.invoke()
                handler.post { useData.invoke(result) }
            } catch (exception: Exception) {
                exception.printStackTrace()
//                handler.post { onError.invoke(exception) }
            }
        }
    }
}