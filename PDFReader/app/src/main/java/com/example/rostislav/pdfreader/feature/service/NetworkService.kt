package com.example.rostislav.pdfreader.feature.service

import android.app.*
import android.content.Intent
import android.os.Build
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.feature.activity.main.MainActivity
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.ProgressResponseBody
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkService : IntentService(NetworkService::class.java.name) {
    private lateinit var network: Network

    override fun onCreate() {
        super.onCreate()
        network = (applicationContext as App).network
    }

    override fun onHandleIntent(intent: Intent?) {
        val url = intent?.getStringExtra("url")
        createNotificationChannel()
        startForeground(1, createNotification(pendingIntentSetup()))
        network.getObservable().notifyObserversChange(Data(PROGRESS, url!!, download(url)))
    }

    private fun download(url: String): ByteArray {
        val client = (OkHttpClient.Builder().addNetworkInterceptor {
            val originalResponse = it.proceed(it.request())
            originalResponse.newBuilder()
                .body(ProgressResponseBody(originalResponse.body!!)
                { progress -> network.getObservable().notifyObserversChange(Data(progress, url, null)) })
                .build()
        }).build()
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute().body!!.bytes()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    private fun createNotification(pendingIntent: PendingIntent): Notification {
        return Notification.Builder(this, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setContentTitle("New file")
            .setStyle(Notification.BigTextStyle().bigText("Downloading..."))
            .build()
    }

    private fun pendingIntentSetup(): PendingIntent {
        val notificationIntent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, notificationIntent, 0)
    }

    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
        const val PROGRESS = 100L
    }
}