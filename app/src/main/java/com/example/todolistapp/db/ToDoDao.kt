package com.example.todolistapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistapp.data.ToDoModal

@Dao
interface ToDoDao {

    @Query("SELECT * FROM to_do_table")
    fun displayAllTodo(): LiveData<List<ToDoModal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todoModel: ToDoModal)

    @Update
    suspend fun updateTodo(todoModel: ToDoModal)

    @Delete
    suspend fun deleteTodo(todoModel: ToDoModal)

    /*@Query("Select * From to_do_table where addReminder = 1")
    suspend fun setUpReminder(todoModel: ToDoModal)*/

}