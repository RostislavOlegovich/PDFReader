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
    val mapOfProgress = mutableMapOf<String, Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FileData> {
        val view = parent.inflate(R.layout.item_book)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<FileData>, position: Int) {
        super.onBindViewHolder(holder, position)
        val itemProgress = items[position].url
        if (mapOfProgress[itemProgress] != null && mapOfProgress[itemProgress]!! != 100) {
            holder.itemView.apply {
                pbDownloading.apply {
                    visible(true)
                    progress = mapOfProgress[itemProgress]!!
                }
                tvPercentage.apply {
                    visible(true)
                    text = resources.getString(
                        R.string.percent_text_view,
                        mapOfProgress[itemProgress]!!.toString(),
                        "%"
                    )
                }
            }
            if (mapOfProgress[itemProgress] != null && mapOfProgress[itemProgress]!! == 100) {
                holder.itemView.apply {
                    pbDownloading.apply {
                        visible(false)
                    }
                    tvPercentage.apply {
                        visible(false)
                    }
                    mapOfProgress.remove(itemProgress)
                }
            }
        }
    }

    fun getItemPosition(progress: Int, url: String) {
        mapOfProgress[url] = progress
        notifyItemChanged(items.indexOfFirst { fileData -> fileData.url == url })
    }

    fun isLoadingNull(position: Int): Boolean {
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