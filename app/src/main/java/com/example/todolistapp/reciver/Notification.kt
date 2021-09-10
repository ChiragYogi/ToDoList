package com.example.todolistapp.reciver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.todolistapp.MainActivity
import com.example.todolistapp.R
import com.example.todolistapp.data.Priority
import com.example.todolistapp.utiles.utills.MY_CHANNEL_ID
import com.example.todolistapp.utiles.utills.MY_CHANNEL_NAME

import kotlin.random.Random



class Notification {

     fun showNotification(
        context: Context, title: String?,
        priority: Priority?, todoDateAndTime: String) {

        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        )


        //Setting Sound for Notification
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        //crating Notification with notification builder
        val notificationBuilder = NotificationCompat.Builder(context, MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("ToDo Name :- ${title?.uppercase()}")
            .setContentText(" Importance :- ${priority?.name}  ToDo Time :- $todoDateAndTime ")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setCategory(NotificationCompat.CATEGORY_ALARM)


        val notificationManger =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //since  android oreo notification chanel is needed

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                MY_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )


            notificationManger.createNotificationChannel(channel)
        }

        val notificationId = Random.nextInt()
        Log.d("ToDoLog","$notificationId")
        notificationManger.notify(notificationId, notificationBuilder.build())

    }




}

