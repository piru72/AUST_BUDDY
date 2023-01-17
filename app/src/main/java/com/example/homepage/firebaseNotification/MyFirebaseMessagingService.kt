package com.example.homepage.firebaseNotification

import android.app.Notification
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

        val builder = CustomNotificationBuilder(applicationContext, "senderId")
            .setSmallIcon(R.drawable.ic_baseline_bug_report_24)
            .setContentTitle(title)
            .setContentText(body)

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())
    }
}

class CustomNotificationBuilder(context: Context, channelId: String) : NotificationCompat.Builder(context, channelId) {

    override fun build(): Notification {
        // customize the notification here
        return super.build()
    }
}

//class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {
//    override fun onTokenRefresh() {
//        val currentThread = Thread.currentThread().name
//        Log.d("Token Generation", "Thread: $currentThread")
//        // ...
//    }
//}