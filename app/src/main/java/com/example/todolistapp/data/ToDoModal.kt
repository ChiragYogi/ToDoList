package com.example.todolistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "to_do_table")
data class ToDoModal(

    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "date")
    val date:String? = DateFormat.getDateInstance().toString(),
    @ColumnInfo(name = "time")
    val time:String? = DateFormat.getTimeInstance().toString(),
    @ColumnInfo(name = "addReminder")
    val addReminder:Boolean = false,
    @ColumnInfo(name = "priority")
    val priority:String,
    @PrimaryKey(autoGenerate = true)
    val id:Long =0
)
