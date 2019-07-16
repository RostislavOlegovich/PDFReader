package com.example.rostislav.pdfreader.utils.extention

import android.view.View

fun View.visible(boolean: Boolean) {
    visibility = if (boolean) {
        View.VISIBLE
    } else {
        View.GONE
    }
}