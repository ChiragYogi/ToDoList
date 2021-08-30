package com.example.todolistapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistapp.data.ToDoModal

@Database(entities = [ToDoModal::class], version = 1,exportSchema = false)
abstract class ToDoDataBase: RoomDatabase() {

    abstract fun todoDao(): ToDoDao

    companion object{

        @Volatile
        private var INSTANCE: ToDoDataBase? = null

        fun getDataBase(context: Context): ToDoDataBase =
                INSTANCE ?: synchronized(this){
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ToDoDataBase::class.java, "todo_database"
            ).build()
    }
}