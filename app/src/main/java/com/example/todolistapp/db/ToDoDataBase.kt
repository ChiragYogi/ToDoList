package com.example.todolistapp.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolistapp.data.Converter
import com.example.todolistapp.data.Priority
import com.example.todolistapp.data.ToDoModal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ToDoModal::class], version = 1,exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDataBase: RoomDatabase() {

    abstract fun todoDao(): ToDoDao

    //populetion room db
    private class ToDoDatabaseCallBack(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE.let { toDoDataBase ->
                scope.launch {
                    val todoDao = toDoDataBase?.todoDao()
                    todoDao?.deleteAll()
                    //adding todo
                    val todo = ToDoModal("Call Chirag","02/09/2021", "02:03 PM"
                        ,priority = Priority.Low, addReminderForToDo = false, todoCompleted = true)
                    val todo2 = ToDoModal("Pay Electricity Bill","02/09/2021","02:03 PM",
                        priority = Priority.Low , addReminderForToDo = false, todoCompleted = false)
                    val todo3 = ToDoModal("Clean kitchen","02/09/2021", "02:03 PM",
                        priority = Priority.Medium, addReminderForToDo = false, todoCompleted = true)
                    val todo4 = ToDoModal("Drink 3lt water","02/09/2021", "02:03 PM",
                        priority = Priority.High , addReminderForToDo = false, todoCompleted = false)
                    todoDao?.insertTodo(todo)
                    todoDao?.insertTodo(todo2)
                    todoDao?.insertTodo(todo3)
                    todoDao?.insertTodo(todo4)


                }

            }
        }
    }

    companion object{

        @Volatile
        private var INSTANCE: ToDoDataBase? = null

        fun getDataBase(context: Context, scope: CoroutineScope): ToDoDataBase {
            return INSTANCE ?: synchronized(this){
                val instence = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDataBase::class.java,"to_do_database"
                ).addCallback(ToDoDatabaseCallBack(scope))

                    .build()
                INSTANCE = instence
                instence
            }
        }
    }
}