package com.qwe7002.telegram_sms

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper {
    companion object {
        private const val CHANNEL_ID = "background_service"
        
        fun createMinimalNotification(context: Context, serviceClass: Class<*>): Notification {
            // Create notification channel with lowest importance
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Background Service",
                    NotificationManager.IMPORTANCE_MIN
                )
                channel.setShowBadge(false)
                channel.enableLights(false)
                channel.enableVibration(false)
                channel.lockscreenVisibility = Notification.VISIBILITY_SECRET
                
                val notificationManager = context.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }
            
            // Create minimal notification
            val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationCompat.Builder(context, CHANNEL_ID)
            } else {
                NotificationCompat.Builder(context)
                    .setPriority(NotificationCompat.PRIORITY_MIN)
            }
            
            // Empty notification with minimal visibility
            return builder
                .setSmallIcon(android.R.color.transparent)
                .setContentTitle("")
                .setContentText("")
                .setOngoing(true)
                .build()
        }
    }
}
