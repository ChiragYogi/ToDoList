package com.example.todolistapp.ui.viemodel

import android.app.Application
import androidx.lifecycle.*
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.db.ToDoDataBase
import com.example.todolistapp.repository.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) :AndroidViewModel(application) {

    private val scope = CoroutineScope(SupervisorJob())

    private val todoDao = ToDoDataBase.getDataBase(application,scope).todoDao()
    private val repository: ToDoRepository = ToDoRepository(todoDao)

    val readAllToDo: LiveData<List<ToDoModal>> = repository.raedAllData
    val sortByHighPriority: LiveData<List<ToDoModal>> = repository.sortByHighPriority
    val sortByMediumPriority: LiveData<List<ToDoModal>> = repository.sortByMediumPriority
    val sortByLowPriority: LiveData<List<ToDoModal>> = repository.sortByLowPriority




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

    fun searchDatabase(query: String): LiveData<List<ToDoModal>>{
        return  repository.searchDatabase(query)
    }

    fun deleteCompletedTodo(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCompletedTodo()
        }
    }





}