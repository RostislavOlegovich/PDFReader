package com.example.rostislav.pdfreader.feature.main

import android.view.View
import android.view.ViewGroup
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseAdapter
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.utils.extension.gone
import com.example.rostislav.pdfreader.utils.extension.inflate
import com.example.rostislav.pdfreader.utils.extension.loadView
import com.example.rostislav.pdfreader.utils.extension.visible
import kotlinx.android.synthetic.main.item_book.view.*
import java.io.File

class MainAdapter : BaseAdapter<FileData, BaseAdapter.BaseViewHolder<FileData>>() {
    private val mapOfProgress = mutableMapOf<String, Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FileData> {
        val view = parent.inflate(R.layout.item_book)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<FileData>, position: Int) {
        super.onBindViewHolder(holder, position)
        val itemProgress = items[position].url
        val currentProgress = mapOfProgress[itemProgress]
        if (isLoading(position) && currentProgress != 100) {
            holder.showProgress(currentProgress!!)
        } else {
            mapOfProgress.remove(itemProgress)
        }
    }

    fun setProgressDownloading(progress: Int, url: String) {
        mapOfProgress[url] = progress
        notifyItemChanged(items.indexOfFirst { fileData -> fileData.url == url })
    }

    fun isLoading(position: Int): Boolean {
        val itemProgress = items[position].url
        return mapOfProgress[itemProgress] != null
    }

    class BookViewHolder(item: View) : BaseAdapter.BaseViewHolder<FileData>(item) {
        override fun bind(type: FileData) {
            itemView.apply {
                ivBookTitle.loadView(itemView.context, File(type.thumbnail))
                tvNameBook.text = type.fileName
                gone(pbDownloading, tvPercentage)
            }
        }

        override fun showProgress(currentProgress: Int) {
            itemView.apply {
                visible(pbDownloading, tvPercentage)
                pbDownloading.progress = currentProgress
                tvPercentage.text = resources.getString(
                    R.string.percent_text_view,
                    currentProgress.toString(),
                    "%"
                )
            }
        }
    }
}
