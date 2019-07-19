package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.base.BaseAdapter
import com.example.rostislav.pdfreader.model.database.room.FileData
import com.example.rostislav.pdfreader.utils.extention.inflate
import com.example.rostislav.pdfreader.utils.extention.loadView
import kotlinx.android.synthetic.main.item_book.view.*
import java.io.File

class MainAdapter(context: Context) :
    BaseAdapter<FileData, BaseAdapter.BaseViewHolder<FileData>>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FileData> {
        val view = parent.inflate(R.layout.item_book)
        return BookViewHolder(view)
    }

    class BookViewHolder(item: View) : BaseAdapter.BaseViewHolder<FileData>(item) {
        override fun bind(type: FileData) {
            itemView.tvNameBook.text = type.fileName
            itemView.ivBookTitle.loadView(itemView.context, File(type.thumbnail))
        }
    }
}