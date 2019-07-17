package com.example.rostislav.pdfreader.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import com.example.rostislav.pdfreader.feature.book.BookActivity
import com.example.rostislav.pdfreader.model.database.room.FileData
import com.example.rostislav.pdfreader.utils.DatabaseCreator
import com.example.rostislav.pdfreader.utils.extention.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<View, Presenter>(), View {

    private val adapter = MainAdapter(DatabaseCreator.fileList)

    override fun assignLayout() = R.layout.activity_main

    override fun createPresenter() = MainPresenter(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecycler()
        presenter.loadAllFiles()
        adapter.itemClickListener = { position, _ ->
            presenter.loadFile(adapter.items[position])
        }
    }

    override fun showView(data: List<FileData>) {
        adapter.replace(data as MutableList<FileData>)
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
        presenter.loadAllFiles()
    }

    private fun initRecycler() {
        LinearSnapHelper().attachToRecyclerView(rvBooks)
        rvBooks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}