package com.example.rostislav.pdfreader.feature.main

import android.view.View
import android.view.ViewGroup
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseAdapter
import com.example.rostislav.pdfreader.model.database.room.FileData
import com.example.rostislav.pdfreader.utils.extention.inflate
import kotlinx.android.synthetic.main.item_book.view.*

class MainAdapter(items: MutableList<FileData>) :
    BaseAdapter<FileData, BaseAdapter.BaseViewHolder<FileData>>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FileData> {
        val view = parent.inflate(R.layout.item_book)
        return BookViewHolder(view)
    }

    class BookViewHolder(item: View) : BaseAdapter.BaseViewHolder<FileData>(item) {
        override fun bind(type: FileData) {
            itemView.tvNameBook.text = type.fileName
            itemView.tvNameBook
        }
    }
}