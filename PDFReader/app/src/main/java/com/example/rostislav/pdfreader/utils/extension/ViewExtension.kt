package com.example.rostislav.pdfreader.utils.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.example.rostislav.pdfreader.R
import java.io.File

fun View.visible(boolean: Boolean) {
    visibility = if (boolean) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@Suppress("unused")
fun View.gone(vararg views: View) = views.forEach { it.visibility = View.GONE }

@Suppress("unused")
fun View.visible(vararg views: View) = views.forEach { it.visibility = View.VISIBLE }

fun ImageView.loadView(context: Context, file: File) {
    Glide.with(context)
        .load(file)
        .placeholder(R.mipmap.ic_launcher)
        .dontAnimate()
        .into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}