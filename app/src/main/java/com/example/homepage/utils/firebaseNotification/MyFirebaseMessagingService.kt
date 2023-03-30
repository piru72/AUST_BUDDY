package com.example.homepage.utils.firebaseNotification

import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.homepage.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Extract the message data and create a notification
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        val builder = CustomNotificationBuilder(applicationContext, "senderId")
            .setSmallIcon(R.drawable.ic_baseline_bug_report_24)
            .setContentTitle(title)
            .setContentText(body)

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        // Send the new token to your server or update your user's FCM token
    }
}

class CustomNotificationBuilder(context: Context, channelId: String) : NotificationCompat.Builder(context, channelId)

