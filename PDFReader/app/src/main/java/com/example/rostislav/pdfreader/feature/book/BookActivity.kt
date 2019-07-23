package com.example.rostislav.pdfreader.feature.book

import android.os.Bundle
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book.*
import java.io.File

class BookActivity : BaseActivity<BookView, BookPresenterImpl>(), BookView {

    override fun assignLayout() = R.layout.activity_book

    override fun createPresenter() = BookPresenterImpl(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val file = (intent.getStringExtra("filePath"))!!
        presenter.readFromStorage(file)
    }

    override fun show(data: File) {
        pdfView.fromFile(data).load()
    }
}