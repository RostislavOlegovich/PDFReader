package com.example.rostislav.pdfreader.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.utils.extension.getNotificationManager

object NotificationUtils {
    private const val CHANNEL_ID = "ForegroundServiceChannel"
    private const val CHANNEL_NAME = "Foreground Service Channel"
    private const val PROGRESS = 100

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            context.getNotificationManager().createNotificationChannel(serviceChannel)
        }
    }

    fun createNotification(
        context: Context,
        progress: Int = 0,
        @StringRes
        contentTitle: Int = R.string.notification_content_title,
        @StringRes
        notificationText: Int = R.string.notification_text,
        @DrawableRes
        drawable: Int = R.drawable.ic_launcher_foreground
    ): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(drawable)
            setAutoCancel(true)
            setProgress(PROGRESS, progress, false)
            setContentTitle(context.getString(contentTitle))
            setStyle(NotificationCompat.BigTextStyle().bigText(context.getString(notificationText)))
        }.build()
    }
}