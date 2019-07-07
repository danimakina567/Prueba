package com.cice.trivialandroid

import android.app.PendingIntent
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        val title  = remoteMessage?.notification?.title
        Log.i("myApp","Title-> ${title}" )

        var message = remoteMessage?.notification?.body
        Log.i("myApp","message-> ${message}" )

        sendNotification("Titulo impuesto","Cuerpo Impuesto")
    }

    private fun sendNotification(notificationTitle: String , notificationContent : String)
    {
        Log.i("myApp","sendNotification" )

        val intent = Intent(this,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent : PendingIntent = PendingIntent.getActivity(this,0,intent,0)

        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_close_session_black_24dp)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setChannelId("12")

        with(NotificationManagerCompat.from(this))
        {
            notify(1234,notificationBuilder.build())
        }


    }








}