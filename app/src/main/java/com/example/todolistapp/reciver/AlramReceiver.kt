package com.example.todolistapp.reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import com.example.todolistapp.data.Priority
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import com.example.todolistapp.utiles.Utiles.ACTION_SET_EXACT


class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {

        val name = intent.getStringExtra("title")
        val priority = intent.getParcelableExtra<Priority>("priority")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val todoTimeAndDate = "$date $time"
        val id = intent.getIntExtra("id", 0)
        Log.d("ToDoLog", "$id id in reciver")
        if (ACTION_SET_EXACT == intent.action){
             if (id >=0){
                val notification = Notification()
                notification.showNotification(context,name,priority,todoTimeAndDate)
                Log.d("ToDoLog","$name $priority $todoTimeAndDate")
            }
        }

    }
}






