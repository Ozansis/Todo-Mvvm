package com.example.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.Todo
import com.example.todoapp.local.TodoDao
import com.example.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TodoListViewModel @Inject constructor(val repo: TodoRepository)  : ViewModel() {



    private val _todosByCategory = MutableLiveData<List<Todo>>()
    val todosByCategory: LiveData<List<Todo>> = _todosByCategory


    fun loadByCategory(cate: String){
        viewModelScope.launch {

            repo.getCategory(cate).collect { list ->

                _todosByCategory.value = list


            }
        }
    }

    fun loadInProgress(){
        viewModelScope.launch {
            repo.loadAllTask().collect { list->
                _todosByCategory.value = list

            }


        }
    }


    fun  loadCompleted(){

        viewModelScope.launch {
            repo.loadCompleted().collect {list->

                _todosByCategory.value = list
            }
        }
    }


    fun deleteTask(task : Todo){

        viewModelScope.launch {
            repo.deletetask(task)

        }

    }



    fun updateTodoCheckState(todo:Todo,isChecked: Boolean){
        viewModelScope.launch {

            var updatedTodo = todo.copy(isDone =isChecked)
            repo.updateTask(updatedTodo)



        }


    }






}