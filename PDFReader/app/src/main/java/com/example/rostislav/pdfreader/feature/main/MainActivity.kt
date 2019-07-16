package com.example.rostislav.pdfreader.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import com.example.rostislav.pdfreader.feature.book.BookActivity
import com.example.rostislav.pdfreader.model.database.room.FileData
import com.example.rostislav.pdfreader.utils.DatabaseCreator
import com.example.rostislav.pdfreader.utils.extention.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<View, Presenter>(), View {

    private val adapter = ListOfBooksAdapter(DatabaseCreator.fileList)

    override fun setLayout() = R.layout.activity_main

    override fun createView() = this

    override fun createPresenter() = MainPresenter(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvBooks.layoutManager = LinearLayoutManager(this)
        presenter.getAll()
        adapter.itemClickListener = { position, _ ->
            presenter.getFromDatabase(adapter.list[position])
        }
    }

    override fun showView(data: MutableList<FileData>) {
        adapter.replace(data)
        rvBooks.adapter = adapter
    }

    override fun error(exception: Throwable) {
        rvBooks.visible(false)
        tvError.visible(true)
        tvError.text = exception.toString()
    }

    override fun openActivity(data: String) {
        val intent = Intent(this, BookActivity::class.java)
        intent.putExtra("filePath", data)
        startActivity(intent)
        presenter.getAll()
    }
}
