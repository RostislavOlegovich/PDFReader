package com.example.rostislav.pdfreader.feature.second

import android.os.Bundle
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book.*
import java.io.File

class BookActivity : BaseActivity<BookView, BookPresenterImpl>(), BookView {

    override fun setLayout() = R.layout.activity_book

    override fun createView() = this

    override fun createPresenter() = BookPresenterImpl(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val file = (intent.getStringExtra("file"))!!
        presenter.readFromStorage(file, this)
    }

    override fun showView(data: File) {
        pdfView.fromFile(data).load()
    }

    override fun error(exception: Throwable) {
    }
}
