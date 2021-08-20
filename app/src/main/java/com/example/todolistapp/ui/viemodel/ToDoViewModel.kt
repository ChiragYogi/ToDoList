package com.example.todolistapp.ui.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.db.ToDoDataBase
import com.example.todolistapp.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application):AndroidViewModel(application) {

     val _readAllData: LiveData<List<ToDoModal>>
    /*val readAllData:LiveData<List<ToDoModal>> = _readAllData*/
    private val repository: ToDoRepository


    init{
        val todoDao = ToDoDataBase.getDataBase(application).todoDao()
        repository = ToDoRepository(todoDao)
        _readAllData = repository.raedAllData
    }

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