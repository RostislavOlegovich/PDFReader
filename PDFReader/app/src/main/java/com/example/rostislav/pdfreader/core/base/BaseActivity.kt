package com.example.rostislav.pdfreader.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView

abstract class BaseActivity<V : MVPView, P : MVPPresenter<V>> : AppCompatActivity(), MVPView {
    lateinit var presenter: P
    lateinit var view: V

    @LayoutRes
    abstract fun assignLayout(): Int

    abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(assignLayout())
        presenter = createPresenter()
        @Suppress("UNCHECKED_CAST")
        view = this as V
        presenter.attach(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettach()
    }

    override fun error(exception: Throwable) {
    }
}