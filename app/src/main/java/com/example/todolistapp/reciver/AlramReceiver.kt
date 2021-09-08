package com.example.todolistapp.reciver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.todolistapp.MainActivity
import com.example.todolistapp.R
import com.example.todolistapp.alaram.SetAlaram

import com.example.todolistapp.utiles.utills.Companion.ACTION_SET_EXACT
import com.example.todolistapp.utiles.utills.Companion.BOOT_COMPLETED


class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val name = intent.getStringExtra("title")
        /* val priority = intent.getStringExtra("priority")*/
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val todoTimeAndDate = "$date $time"

        when (intent.action) {
            ACTION_SET_EXACT -> {

                try {
                    showNotification(context, name!!, todoTimeAndDate)
                    Log.d("ToDoLog", "${System.currentTimeMillis()}")
                    Log.d("ToDoLog", "$name  $todoTimeAndDate in reciver")
                } catch (e: Exception) {
                    Log.d("ToDoLog", "$e")
                }

            }
            BOOT_COMPLETED -> {
                try {
                    showNotification(context, name!!, todoTimeAndDate)
                    Log.d("ToDoLog", "${System.currentTimeMillis()}")
                    Log.d("ToDoLog", "$name  $todoTimeAndDate in reciver")
                } catch (e: Exception) {
                    Log.d("ToDoLog", "$e")
                }
            }

        }
    }


    companion object {
        const val MY_CHANNEL_ID = "999"
        const val MY_CHANNEL_NAME = "ToDo App Notification"

    }


    //Building Notification
    private fun showNotification(context: Context, title: String, timeandDate: String) {

        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE or FLAG_UPDATE_CURRENT
        )

        //Setting Sound for Notification
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        //crating Notification with notification builder
        val notificationBuilder = NotificationCompat.Builder(context, MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)

            .setContentText(timeandDate)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

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

        notificationManger.notify(0, notificationBuilder.build())

    }

}