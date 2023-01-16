package com.example.homepage.firebaseNotification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.homepage.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Extract the message data and create a notification
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        val builder = NotificationCompat.Builder(applicationContext, "channelId")
            .setSmallIcon(R.drawable.ic_baseline_bug_report_24)
            .setContentTitle(title)
            .setContentText(body)

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())
    }
}