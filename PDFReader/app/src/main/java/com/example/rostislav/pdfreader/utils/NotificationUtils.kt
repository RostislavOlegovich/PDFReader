package com.example.rostislav.pdfreader.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.utils.extension.getNotificationBuilder
import com.example.rostislav.pdfreader.utils.extension.getNotificationManager
import com.example.rostislav.pdfreader.utils.system.AndroidAPIChecker

object NotificationUtils {
    private const val CHANNEL_ID = "ForegroundServiceChannel"
    private const val CHANNEL_NAME = "Foreground Service Channel"
    private const val PROGRESS = 100

    private fun createNotificationChannel(context: Context) {
        if (AndroidAPIChecker.checkAndroidAPI()) {
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
    ): Notification = createNotification(
        context,
        progress,
        context.getString(contentTitle),
        context.getString(notificationText),
        drawable
    )

    fun createNotification(
        context: Context,
        progress: Int = 0,
        contentTitle: String,
        notificationText: String,
        @DrawableRes
        drawable: Int = R.drawable.ic_launcher_foreground
    ): Notification {
        createNotificationChannel(context)
        return context.getNotificationBuilder(CHANNEL_ID).apply {
            setSmallIcon(drawable)
            setAutoCancel(true)
            setProgress(PROGRESS, progress, false)
            setContentTitle(contentTitle)
            setStyle(NotificationCompat.BigTextStyle().bigText(notificationText))
        }.build()
    }
}