package com.example.rostislav.pdfreader.feature.service

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import com.example.rostislav.pdfreader.R
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.feature.activity.main.MainActivity
import com.example.rostislav.pdfreader.model.network.Network
import org.jetbrains.anko.doAsync

class NetworkService : Service(), Observer<Data> {
    private lateinit var network: Network

    override fun onObserve(data: Data) {
        createNotificationChannel()
        createNotification(pendingIntentSetup(), data)
    }

    override fun onError(exception: Throwable) {
    }

    override fun onCreate() {
        super.onCreate()
        network = (applicationContext as App).network
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val url = intent?.getStringExtra("url")
        doAsync {
            network.downloadFromNetwork(url!!)
        }
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
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

    private fun createNotification(pendingIntent: PendingIntent, data: Data): Notification {
        return Notification.Builder(this, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setProgress(PROGRESS.toInt(), data.progress.toInt(), false)
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
        const val SERVICE_FOREGROUND_ID = 1
        const val PROGRESS = 100L

//        private fun createIntent(context: Context, url: String):Intent{
//
//        }
    }
}