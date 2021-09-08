package com.example.todolistapp.utiles

import com.example.todolistapp.data.Priority

class  utills {
    companion object{

        val priority = listOf("High","Medium","Low")

        const val ACTION_SET_EXACT = "com.example.todolistapp.reciver.AlaramReceiver"
        const val BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED"


        fun selectPriority(priority: String): Priority {
            return when(priority){

                "High" -> {
                    Priority.HIGh
                }
                "Medium" -> {
                    Priority.MEDIUM
                }
                "LOW" -> {
                    Priority.LOW
                }

                else -> Priority.LOW
            }

        }

    }

}