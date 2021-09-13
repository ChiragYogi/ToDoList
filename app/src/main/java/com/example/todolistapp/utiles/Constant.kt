package com.example.todolistapp.utiles


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.example.todolistapp.R
import com.example.todolistapp.data.Priority


object  Utiles {


    val priority = listOf("High", "Medium", "Low")

    //intent Action for pending intent for alarm manger
    const val ACTION_SET_EXACT = "com.example.todolistapp.reciver.AlaramReceiver"


    //channel name for notification and Id
    const val MY_CHANNEL_ID = "To Do App Notification Channel Id"
    const val MY_CHANNEL_NAME = "ToDo App Notification"


    // Passing priority to string for room db
    fun selectPriority(priority: String): Priority {
        return when (priority) {

            "High" -> {
                Priority.High
            }
            "Medium" -> {
                Priority.Medium
            }
            "LOW" -> {
                Priority.Low
            }

            else -> Priority.Low
        }

    }


    //setting backgroundColor on CardView
    @RequiresApi(Build.VERSION_CODES.M)
    fun bacgroundColor(cardView: CardView, priority: Priority) {
        when (priority) {
            Priority.High -> {
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))
            }
            Priority.Medium -> {
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
            }
            Priority.Low -> {
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
            }
        }


    }
}



