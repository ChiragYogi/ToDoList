package com.example.todolistapp.repository

import androidx.lifecycle.LiveData
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.db.ToDoDao
import com.example.todolistapp.db.ToDoDataBase

class ToDoRepository(private val todoDao:ToDoDao) {


    val raedAllData: LiveData<List<ToDoModal>> = todoDao.displayAllTodo()
    val sortByHighPriority: LiveData<List<ToDoModal>> = todoDao.sortByHighPriority()
    val sortByMediumPriority: LiveData<List<ToDoModal>> = todoDao.sortByMediumPriority()
    val sortByLowPriority: LiveData<List<ToDoModal>> = todoDao.sortByLowPriority()
    val readCompletedTodo: LiveData<List<ToDoModal>> = todoDao.getUpdatedTodo()

   suspend fun addTodo(toDoModal: ToDoModal){
        todoDao.insertTodo(toDoModal)
    }

    suspend fun deleteTodo(toDoModal: ToDoModal) {
        todoDao.deleteTodo(toDoModal)
    }

    suspend fun updateTodo(toDoModal: ToDoModal) {
        todoDao.updateTodo(toDoModal)
    }


    suspend fun deleteCompletedTodo(){
        todoDao.deleteUpdatedToDo()
    }

    fun searchDatabase(query: String): LiveData<List<ToDoModal>>{
        return todoDao.searchDatabase(query)
    }






}