package com.example.rostislav.pdfreader.core.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseAdapter.BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {
    val items: MutableList<T> = mutableListOf()
    var itemClickListener: ((Int, View) -> Unit)? = null

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION && holder.adapterPosition < itemCount) {
                itemClickListener?.invoke(holder.adapterPosition, it)
            }
        }
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    open fun replace(itemsList: MutableList<T>) {
        items.clear()
        items.addAll(itemsList)
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder<T>(item: View) : RecyclerView.ViewHolder(item) {
        abstract fun bind(type: T)
        abstract fun showProgress(currentProgress: Int)
    }
}