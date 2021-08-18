package com.example.todolistapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistapp.data.ToDoModal

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todoModel:ToDoModal)

    @Query("SELECT * FROM to_do_table")
    suspend fun displayAllTodo():LiveData<List<ToDoModal>>

   @Update
   suspend fun updateUser(todoModel: ToDoModal)

    @Query("DELETE FROM to_do_table")
    suspend fun deleteTodo()

    @Query("Select * From to_do_table where addReminder=1")
    suspend fun setUpReminder():LiveData<List<ToDoModal>>

}