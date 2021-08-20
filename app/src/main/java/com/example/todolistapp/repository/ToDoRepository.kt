package com.example.todolistapp.repository

import androidx.lifecycle.LiveData
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.db.ToDoDao
import com.example.todolistapp.db.ToDoDataBase

class ToDoRepository(private val todoDao:ToDoDao) {

    //second way to do it

    val raedAllData: LiveData<List<ToDoModal>> = todoDao.displayAllTodo()

    suspend fun addTodo(toDoModal: ToDoModal){
        todoDao.insertTodo(toDoModal)
    }

    suspend fun deleteTodo(toDoModal: ToDoModal) {
        todoDao.deleteTodo(toDoModal)
    }

    suspend fun updateTodo(toDoModal: ToDoModal) {
        todoDao.updateTodo(toDoModal)
    }

   /* suspend fun getReminderTodo(toDoModal: ToDoModal) {
        todoDao.setUpReminder(toDoModal)
    }*/




    //one way to do it
   /* suspend fun getAllToDo() = database.todoDao().displayAllTodo()

    suspend fun insertTodo(toDoModal: ToDoModal) = database.todoDao().insertTodo(toDoModal)

    suspend fun updateTodo(toDoModal: ToDoModal) = database.todoDao().updateTodo(toDoModal)*/


}