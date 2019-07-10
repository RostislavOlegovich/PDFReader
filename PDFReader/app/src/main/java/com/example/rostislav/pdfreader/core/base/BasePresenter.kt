package com.example.rostislav.pdfreader.core.base

import android.content.Context
import android.os.Looper
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.feature.mvp.MVPPresenter
import com.example.rostislav.pdfreader.feature.mvp.MVPView
import com.example.rostislav.pdfreader.model.network.Network

abstract class BasePresenter<V : MVPView>(context: Context) : MVPPresenter<V> {
    val network: Network = (context as App).network
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

    inline fun doAsync(
        crossinline blockA: () -> Unit,
        crossinline blockB: () -> Unit
    ) {
        executor.execute {
            blockA.invoke()
            handler.post { blockB.invoke() }
        }
    }
}