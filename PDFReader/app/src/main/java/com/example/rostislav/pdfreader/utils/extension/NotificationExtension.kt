package com.example.rostislav.pdfreader.utils.extension

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.rostislav.pdfreader.utils.system.AndroidAPIChecker

fun Context.getNotificationManager() =
    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

fun Context.getNotificationBuilder(channelId: String): NotificationCompat.Builder {
    return if (AndroidAPIChecker.checkAndroidAPI()) {
        NotificationCompat.Builder(this, channelId)
    } else {
        NotificationCompat.Builder(this)
    }
}