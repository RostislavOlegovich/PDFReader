package com.example.rostislav.pdfreader.utils.extention

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

fun ImageView.loadView(context: Context, file: File) {
    Glide.with(context)
        .load(file)
        .error(R.mipmap.ic_launcher)
        .into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}