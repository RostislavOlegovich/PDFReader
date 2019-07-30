package com.example.rostislav.pdfreader.feature.activity.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.rostislav.pdfreader.core.base.BaseActivity
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.feature.activity.book.BookActivity
import com.example.rostislav.pdfreader.utils.extension.openActivity
import com.example.rostislav.pdfreader.utils.extension.visible
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<View, Presenter>(), View {
    private lateinit var adapter: MainAdapter

    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    override fun assignLayout() = com.example.rostislav.pdfreader.R.layout.activity_main

    override fun create() = MainPresenter(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadAllFiles()
        initRecycler()
        adapter.itemClickListener = { position, _ ->
            if (adapter.isLoadingExist(position)) {
                presenter.loadFile(adapter.items[position])
            } else {
                Toast.makeText(this, "File is already loading", Toast.LENGTH_SHORT).show()
            }
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

    override fun showThumbnail() {
        presenter.loadAllFiles()
    }

    override fun fileDownloaded(data: String) {
        openActivity(BookActivity::class.java) { putString("filePath", data) }
        presenter.loadAllFiles()
    }

    override fun loadingProgress(progress: Long, url: String) {
        adapter.setItemPosition(progress.toInt(), url)
    }

    private fun initRecycler() {
        adapter = MainAdapter()
        val animator = rvBooks.itemAnimator
        if (animator is SimpleItemAnimator) animator.supportsChangeAnimations = false
        LinearSnapHelper().attachToRecyclerView(rvBooks)
        rvBooks.layoutManager = layoutManager
        rvBooks.adapter = adapter
    }
}