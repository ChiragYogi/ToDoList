package com.example.todolistapp.ui.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.db.ToDoDao
import com.example.todolistapp.db.ToDoDataBase
import com.example.todolistapp.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application):AndroidViewModel(application) {

    private val todoDao = ToDoDataBase.getDataBase(application).todoDao()
    private val repository: ToDoRepository = ToDoRepository(todoDao)

    val readAllToDo: LiveData<List<ToDoModal>> = repository.raedAllData



    fun addTodo(toDoModal: ToDoModal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(toDoModal)
        }
    }

    fun deleteTodo(toDoModal: ToDoModal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(toDoModal)
        }
    }
    fun updateTodo(toDoModal: ToDoModal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(toDoModal)
        }
    }

    fun onToDoChecked(toDoModal: ToDoModal, isChecked: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(toDoModal.copy(todoCompleted = isChecked))
        }
   }
/*
    fun reminderTodo(toDoModal: ToDoModal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(toDoModal)
        }
    }*/

   /* fun reminderTodo(toDoModal: ToDoModal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getReminderTodo(toDoModal)
        }
    }*/


}