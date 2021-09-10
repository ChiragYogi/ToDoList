package com.example.todolistapp.reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.todolistapp.alaram.SetAlaram
import com.example.todolistapp.data.Priority
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.utiles.utills.ACTION_SET_EXACT
import com.example.todolistapp.utiles.utills.BOOT_COMPLETED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {

        if (ACTION_SET_EXACT == intent.action){
            val name = intent.getStringExtra("title")
            val priority = intent.getParcelableExtra<Priority>("priority")
            val date = intent.getStringExtra("date")
            val time = intent.getStringExtra("time")
            val todoTimeAndDate = "$date $time"
            val id = intent.getIntExtra("id", 0)
            Log.d("ToDoLog", "$id id in reciver")

            if (id >=0){
                val notification = Notification()
                notification.showNotification(context,name,priority,todoTimeAndDate)
                Log.d("ToDoLog","$name $priority $todoTimeAndDate")
            }
        }

    }
}






