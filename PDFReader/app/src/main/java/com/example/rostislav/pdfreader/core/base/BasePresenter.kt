package com.example.rostislav.pdfreader.core.base

import android.content.Context
import android.os.Looper
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView

abstract class BasePresenter<V : MVPView>(context: Context) : MVPPresenter<V> {
    val repository = (context as App).repository
    val executor = (context as App).executor
    val handler = android.os.Handler(Looper.getMainLooper())

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
        crossinline useData: (T) -> Unit,
        crossinline onError: (Throwable) -> Unit = this::onError
    ) {
        executor.execute {
            try {
                val result = requestData.invoke()
                handler.post { useData.invoke(result) }
            } catch (exception: Exception) {
                exception.printStackTrace()
                handler.post { onError.invoke(exception) }
            }
        }
    }
}