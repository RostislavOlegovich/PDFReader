package com.example.rostislav.pdfreader.feature.main

import android.os.Bundle
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : BaseActivity(), View {
    lateinit var presenter: MainPresenter
    private val url = "https://www.telc.net/fileadmin/user_upload/telc_english_b2_uebungstest_1.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        presenter.attach(this)
        presenter.downloadView(url, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettach()
    }

    override fun showView(file: File) {
        super.showView(file)
        pdfView.fromFile(file).load()
    }
}
