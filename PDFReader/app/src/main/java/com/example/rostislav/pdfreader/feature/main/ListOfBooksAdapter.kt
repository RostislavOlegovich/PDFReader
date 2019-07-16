package com.example.rostislav.pdfreader.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseAdapter
import com.example.rostislav.pdfreader.model.database.room.FileData
import kotlinx.android.synthetic.main.item_book.view.*

class ListOfBooksAdapter(list: MutableList<FileData>) :
    BaseAdapter<FileData, BaseAdapter.BaseViewHolder<FileData>>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FileData> {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    class BookViewHolder(item: View) : BaseAdapter.BaseViewHolder<FileData>(item) {
        override fun bind(type: FileData) {
            itemView.tvItemBook.text = type.fileName
        }
    }
}
