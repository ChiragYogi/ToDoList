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

        fun getDataBase(context: Context): ToDoDataBase{

            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,ToDoDataBase::class.java,"to_do_table"
                ).build()

                INSTANCE = instance
              return  instance
            }
        }
    }
}