package com.example.rostislav.pdfreader.utils.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

fun <T> Activity.openActivity(activity: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, activity)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun <T> Context.createIntent(clazz: Class<T>, action: String? = null, extras: Bundle.() -> Unit = {}): Intent {
    val intent = Intent(this, clazz)
    intent.putExtras(Bundle().apply(extras))
    intent.action = action
    return intent
}