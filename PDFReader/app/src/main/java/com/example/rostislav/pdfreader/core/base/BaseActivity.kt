package com.example.rostislav.pdfreader.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.rostislav.pdfreader.feature.mvp.MVPPresenter
import com.example.rostislav.pdfreader.feature.mvp.MVPView

abstract class BaseActivity<V : MVPView, P : MVPPresenter<V>> : AppCompatActivity() {
    lateinit var presenter: P
    lateinit var view: V

    @LayoutRes
    abstract fun setLayout(): Int

    abstract fun createView(): V

    abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        presenter = createPresenter()
        view = createView()
        presenter.attach(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettach()
    }
}
