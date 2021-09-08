package com.example.todolistapp.alaram

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

import android.util.Log
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.reciver.AlarmReceiver
import com.example.todolistapp.utiles.utills.Companion.ACTION_SET_EXACT
import java.text.DateFormat

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

import java.util.*
import java.util.logging.Level.parse

class SetAlaram(private val context: Context) {


    private val alramManager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun setAlaramForRemiderToDO(title: String, date: String,time: String) {


        val intent = Intent(context, AlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent.apply {
                action = ACTION_SET_EXACT
                putExtra("title",title)
                putExtra("date",date)
                putExtra("time",time)

            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        )

        val remiderDateAndTime = "$date $time"
        Log.d("ToDoLog","$remiderDateAndTime in setAlarm")

        val formate = SimpleDateFormat("dd/MM/yyyy h:mm a")
        val todoTime = formate.parse(remiderDateAndTime)


        Log.d("ToDoLog","$todoTime in  befor setAlaram")
        Log.d("ToDoLog","${System.currentTimeMillis()}")

       alramManager.let {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alramManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                     todoTime.time ,
                    pendingIntent
                )
                Log.d("ToDoLog","$todoTime in setAlaram in alaram manager")
            } else {
                alramManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    todoTime.time,
                    pendingIntent
                )

            }
        }


    }

}


