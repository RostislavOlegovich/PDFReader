package com.example.rostislav.pdfreader.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.feature.book.BookActivity
import com.example.rostislav.pdfreader.utils.extention.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<View, Presenter>(), View {

    private lateinit var adapter: MainAdapter

    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    override fun assignLayout() = R.layout.activity_main

    override fun createPresenter() = MainPresenter(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadAllFiles()
        initRecycler()
        adapter.itemClickListener = { position, _ ->
            presenter.loadFile(adapter.items[position])
        }
    }

    override fun show(data: List<FileData>) {
        adapter.replace(data as MutableList<FileData>)
    }

    override fun error(exception: Throwable) {
        rvBooks.visible(false)
        tvError.visible(true)
        tvError.text = exception.toString()
    }

    override fun fileDownloaded(data: String) {
        val intent = Intent(this, BookActivity::class.java)
        intent.putExtra("filePath", data)
        startActivity(intent)
        presenter.loadAllFiles()
    }

    override fun loadingProgress(progress: Long, url: String) {
        val progressBar = layoutManager.findViewByPosition(adapter.getItemPosition(url))
            ?.findViewById<ContentLoadingProgressBar>(R.id.pbDownloading)
        val textView = layoutManager.findViewByPosition(adapter.getItemPosition(url))
            ?.findViewById<AppCompatTextView>(R.id.tvPercentage)
        progressBar?.apply {
            visible(true)
            setProgress(progress.toInt())
        }
        textView?.apply {
            visible(true)
            text = getString(R.string.percent_text_view, progress.toInt().toString(), "%")
        }
    }

    private fun initRecycler() {
        adapter = MainAdapter()
        LinearSnapHelper().attachToRecyclerView(rvBooks)
        rvBooks.layoutManager = layoutManager
        rvBooks.adapter = adapter
    }
}