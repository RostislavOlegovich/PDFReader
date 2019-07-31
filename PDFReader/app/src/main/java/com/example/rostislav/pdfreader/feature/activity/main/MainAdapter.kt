package com.example.rostislav.pdfreader.feature.activity.main

import android.view.View
import android.view.ViewGroup
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseAdapter
import com.example.rostislav.pdfreader.entity.FileData
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
        if (currentProgress != null && currentProgress != 100) {
            holder.itemView.apply {
                pbDownloading.apply {
                    visible(true)
                    progress = currentProgress
                }
                tvPercentage.apply {
                    visible(true)
                    text = resources.getString(
                        R.string.percent_text_view,
                        currentProgress.toString(),
                        "%"
                    )
                }
            }
            if (currentProgress == 100) {
                holder.itemView.apply {
                    pbDownloading.apply {
                        visible(false)
                    }
                    tvPercentage.apply {
                        visible(false)
                    }
                }
            }
        }
    }

    fun setItemPosition(progress: Int, url: String) {
        mapOfProgress[url] = progress
        notifyItemChanged(items.indexOfFirst { fileData -> fileData.url == url })
    }

    fun isLoadingExist(position: Int): Boolean {
        val itemProgress = items[position].url
        return mapOfProgress[itemProgress] == null
    }

    class BookViewHolder(item: View) : BaseAdapter.BaseViewHolder<FileData>(item) {
        override fun bind(type: FileData) {
            itemView.apply {
                ivBookTitle.loadView(itemView.context, File(type.thumbnail))
                tvNameBook.text = type.fileName
                pbDownloading.visible(false)
                tvPercentage.visible(false)
            }
        }
    }
}