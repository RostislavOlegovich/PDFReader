package com.example.rostislav.pdfreader.feature.main

import android.os.Bundle
import com.example.rostislav.pdfreader.BuildConfig
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : BaseActivity<View, Presenter>(), View {

    override fun setLayout() = R.layout.activity_main

    override fun createView() = this

    override fun createPresenter() = MainPresenter(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)
        presenter.downloadView(BuildConfig.URL, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettach()
    }

    override fun showView(file: File) {
        pdfView.fromFile(file).load()
    }

    override fun error(e: Any) {
        pdfView.visibility = android.view.View.GONE
        tvError.visibility = android.view.View.VISIBLE
        tvError.text = e.toString()
    }
}
