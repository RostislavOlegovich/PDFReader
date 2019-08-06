package com.example.rostislav.pdfreader.utils.extension

import android.app.NotificationManager
import android.content.Context

fun Context.getNotificationManager() =
    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
