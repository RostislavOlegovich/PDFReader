package com.example.rostislav.pdfreader.feature.main

import android.view.View
import android.view.ViewGroup
import com.example.rostislav.pdfreader.core.base.BaseAdapter
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.utils.extension.inflate
import com.example.rostislav.pdfreader.utils.extension.loadView
import com.example.rostislav.pdfreader.utils.extension.visible
import kotlinx.android.synthetic.main.item_book.view.*
import java.io.File

class MainAdapter : BaseAdapter<FileData, BaseAdapter.BaseViewHolder<FileData>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FileData> {
        val view = parent.inflate(com.example.rostislav.pdfreader.R.layout.item_book)
        return BookViewHolder(view)
    }

    fun getItemPosition(url: String): Int {
        val item = items.single { FileData -> FileData.url == url }
        return items.indexOf(item)
    }

    class BookViewHolder(item: View) : BaseAdapter.BaseViewHolder<FileData>(item) {
        override fun bind(type: FileData) {
            itemView.tvNameBook.text = type.fileName
            itemView.pbDownloading.visible(false)
            itemView.tvPercentage.visible(false)
            itemView.ivBookTitle.loadView(itemView.context, File(type.thumbnail))
        }
    }
}