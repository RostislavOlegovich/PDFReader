package com.example.rostislav.pdfreader.core.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseAdapter.BaseViewHolder<T>>(val list: MutableList<T>) :
    RecyclerView.Adapter<VH>() {

    var itemClickListener: ((Int, View) -> Unit)? = null

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION && holder.adapterPosition < itemCount)
                itemClickListener?.invoke(holder.adapterPosition, it)
        }
        holder.bind(list[position])
    }

    open fun updateRecyclerView(items: MutableList<T>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder<T>(item: View) : RecyclerView.ViewHolder(item) {
        abstract fun bind(type: T)
    }
}