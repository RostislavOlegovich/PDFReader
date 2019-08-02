package com.example.rostislav.pdfreader.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import com.example.rostislav.pdfreader.R

object NotificationUtils {
    private const val CHANNEL_ID = "ForegroundServiceChannel"
    private const val CHANNEL_NAME = "Foreground Service Channel"
    private const val PROGRESS = 100
    private const val CONTENT_TITLE = "New file"
    private const val NOTIFICATION_TEXT = "Downloading..."

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(serviceChannel)
        }
    }

    fun createNotification(context: Context): Notification {
        return Notification.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setAutoCancel(true)
            setProgress(PROGRESS, PROGRESS, false)
            setContentTitle(CONTENT_TITLE)
            style = Notification.BigTextStyle().bigText(NOTIFICATION_TEXT)
        }.build()
    }
}