package com.example.todolistapp.utiles

import com.example.todolistapp.data.Priority
import com.example.todolistapp.data.ToDoModal

object  utills {


        val priority = listOf("High","Medium","Low")

        const val ACTION_SET_EXACT = "com.example.todolistapp.reciver.AlaramReceiver"
        const val BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED"

        const val MY_CHANNEL_ID = "To Do App Notification Channel Id"
        const val MY_CHANNEL_NAME = "ToDo App Notification"


        fun selectPriority(priority: String): Priority {
            return when(priority){

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




    }

