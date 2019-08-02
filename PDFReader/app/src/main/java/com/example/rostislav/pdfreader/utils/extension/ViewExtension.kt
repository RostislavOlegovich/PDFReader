package com.example.rostislav.pdfreader.utils.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
        .placeholder(R.mipmap.ic_launcher)
        .dontAnimate()
        .into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun <T> Activity.openActivity(activity: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, activity)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun <T> Context.createIntent(clazz: Class<T>, extras: Bundle.() -> Unit = {}): Intent {
    val intent = Intent(this, clazz)
    intent.putExtras(Bundle().apply(extras))
    return intent
}