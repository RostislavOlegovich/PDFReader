package com.example.rostislav.pdfreader.core.base

import android.content.Context
import android.os.Looper
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.feature.mvp.MVPPresenter
import com.example.rostislav.pdfreader.feature.mvp.MVPView
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.file.FileManager
import com.example.rostislav.pdfreader.model.network.Network

abstract class BasePresenter<V : MVPView>(context: Context) : MVPPresenter<V> {
    val network: Network = (context as App).network
    val database: Database = (context as App).database
    val executor = (context as App).executor
    val fileManager: FileManager = (context as App).fileManager
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
        crossinline blockA: () -> T,
        crossinline blockB: (T) -> Unit,
        crossinline onError: (Throwable) -> Unit
    ) {
        executor.execute {
            try {
                val result = blockA.invoke()
                handler.post { blockB.invoke(result) }
            } catch (exception: Exception) {
                exception.printStackTrace()
                handler.post { onError.invoke(exception) }
            }
        }
    }
}