package com.example.rostislav.pdfreader.feature.main

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.feature.book.BookActivity
import com.example.rostislav.pdfreader.utils.extension.findView
import com.example.rostislav.pdfreader.utils.extension.openActivity
import com.example.rostislav.pdfreader.utils.extension.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<View, Presenter>(), View {

    private lateinit var adapter: MainAdapter

    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    override fun assignLayout() = R.layout.activity_main

    override fun create() = MainPresenter(applicationContext)

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
        openActivity(BookActivity::class.java) { putString("filePath", data) }
        presenter.loadAllFiles()
    }

    override fun loadingProgress(progress: Long, url: String) {
        val position = adapter.getItemPosition(url)
        val progressBar =
            layoutManager.findView<ContentLoadingProgressBar>(position, R.id.pbDownloading)
        val textView = layoutManager.findView<AppCompatTextView>(position, R.id.tvPercentage)
        progressBar?.apply {
            visible(true)
            setProgress(progress.toInt())
        }
        textView?.apply {
            visible(true)
            text = getString(R.string.percent_text_view, progress.toString(), "%")
        }
    }

    private fun initRecycler() {
        adapter = MainAdapter()
        LinearSnapHelper().attachToRecyclerView(rvBooks)
        rvBooks.layoutManager = layoutManager
        rvBooks.adapter = adapter
    }
}