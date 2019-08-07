package com.example.rostislav.pdfreader.feature.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseActivity
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.feature.book.BookActivity
import com.example.rostislav.pdfreader.utils.StringUtils
import com.example.rostislav.pdfreader.utils.extension.openActivity
import com.example.rostislav.pdfreader.utils.extension.visible
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

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
            if (adapter.isLoading(position)) {
                toast(resources.getString(R.string.toast_text))
            } else {
                presenter.loadFile(adapter.items[position].url)
            }
        }
        presenterListener()
    }

    override fun error(exception: Throwable) {
        rvBooks.visible(false)
        tvError.visible(true)
        tvError.text = exception.toString()
    }

    private fun initRecycler() {
        adapter = MainAdapter()
        val animator = rvBooks.itemAnimator
        if (animator is SimpleItemAnimator) animator.supportsChangeAnimations = false
        LinearSnapHelper().attachToRecyclerView(rvBooks)
        rvBooks.layoutManager = layoutManager
        rvBooks.adapter = adapter
    }

    private fun showThumbnail() {
        presenter.loadAllFiles()
    }

    private fun fileDownloaded(url: String) {
        openActivity(BookActivity::class.java) { putString(StringUtils.getExtraStringIntent(), url) }
        presenter.loadAllFiles()
    }

    private fun loadingProgress(progress: Long, url: String) {
        adapter.setProgressDownloading(progress.toInt(), url)
    }

    private fun show(data: List<FileData>) {
        adapter.replace(data as MutableList<FileData>)
    }

    private fun presenterListener() {
        presenter
            .loadingProgressLD
            .observe(this, Observer { data -> loadingProgress(data.progress, data.url) })
        presenter
            .fileDownloadedLD
            .observe(this, Observer { url -> fileDownloaded(url) })
        presenter
            .showListLD
            .observe(this, Observer { fileDataList -> show(fileDataList) })
        presenter
            .showThumbnailLD
            .observe(this, Observer { showThumbnail() })
    }
}