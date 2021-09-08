package com.example.todolistapp.db

import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolistapp.data.ToDoModal

@Dao
interface ToDoDao {

    @Query("SELECT * FROM TO_DO_TABLE ORDER BY id ASC")
    fun displayAllTodo(): LiveData<List<ToDoModal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todoModel: ToDoModal)

    @Update
    suspend fun updateTodo(todoModel: ToDoModal)

    @Delete
    suspend fun deleteTodo(todoModel: ToDoModal)

    @Query("Select * From to_do_table where addReminder= 1")
    fun setUpReminder(): LiveData<List<ToDoModal>>

    @Query("DELETE FROM to_do_table WHERE  isCompleted= 1")
    suspend fun deleteUpdatedToDo()

    @Query("SELECT * FROM to_do_table where title LIKE '%' || :searchQuery || '%'")
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoModal>>


    @Query("SELECT * FROM to_do_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<ToDoModal>>

    @Query("SELECT * FROM to_do_table ORDER BY CASE WHEN priority LIKE 'M%' THEN 1 WHEN priority LIKE 'L%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByMediumPriority(): LiveData<List<ToDoModal>>

    @Query("SELECT * FROM to_do_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<ToDoModal>>

    @Query("Delete from to_do_table")
    fun deleteAll()


}