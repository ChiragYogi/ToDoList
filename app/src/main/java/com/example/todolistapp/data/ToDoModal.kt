package com.example.todolistapp.data

import android.widget.RadioButton
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @ColumnInfo(name = "addReminder")
    val addReminder:Boolean = false,
    @ColumnInfo(name = "priority")
    val priority: String?

)
