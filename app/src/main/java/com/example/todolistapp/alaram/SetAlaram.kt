package com.example.todolistapp.alaram

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.reciver.AlarmReceiver
import com.example.todolistapp.utiles.putParcelableExtra
import com.example.todolistapp.utiles.Utiles.ACTION_SET_EXACT
import java.text.SimpleDateFormat
import java.util.*


object SetAlaram {




  fun setAlarmForRemniderToDO(context: Context,toDoModal: ToDoModal) {


      val alramManager: AlarmManager? =
          context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

      val intent = Intent(context, AlarmReceiver::class.java)


      val pendingIntent = PendingIntent.getBroadcast(
          context,
          toDoModal.id,
          intent.apply {
              action = ACTION_SET_EXACT
              putExtra("title", toDoModal.title)
              putExtra("date", toDoModal.date)
              putExtra("time", toDoModal.time)
              putParcelableExtra("priority", toDoModal.priority)
              putExtra("id", toDoModal.id)
              Log.d("ToDoLog", "$toDoModal.id")
          },
          PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT

      )


      val remiderDateAndTime = "${toDoModal.date} ${toDoModal.time}"
      Log.d("ToDoLog", "$remiderDateAndTime in setAlarm")

      val formate = SimpleDateFormat("dd/MM/yyyy h:mm a")
      val todoTime = formate.parse(remiderDateAndTime)
      val nowTime = Calendar.getInstance().time


      Log.d("ToDoLog", "$todoTime in  befor setAlaram")
      Log.d("ToDoLog", "${System.currentTimeMillis()}")

      if (todoTime > nowTime)  {
          alramManager?.let {

              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                  alramManager.setExactAndAllowWhileIdle(
                      AlarmManager.RTC_WAKEUP,
                      todoTime.time,
                      pendingIntent
                  )
                  Log.d("ToDoLog", "$todoTime in setAlaram in alaram manager")
              } else {
                  alramManager.setExact(
                      AlarmManager.RTC_WAKEUP,
                      todoTime.time,
                      pendingIntent
                  )

              }
          }


      }else{
          alramManager?.cancel(pendingIntent)
      }
  }





}


