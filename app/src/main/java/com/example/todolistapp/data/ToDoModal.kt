package com.example.todolistapp.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "to_do_table")

data class ToDoModal(

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time")
    val time: String,
   @ColumnInfo(name = "priority")
    val priority: String,
    @ColumnInfo(name = "addReminder")
    var addReminderForToDo: Boolean = false,
    @ColumnInfo(name = "isCompleted")
    var isCompletedForToDo: Boolean = false
): Serializable