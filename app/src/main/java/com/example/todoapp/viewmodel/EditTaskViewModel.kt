package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditTaskViewModel  @Inject constructor(val repo: TodoRepository): ViewModel() {





     fun updateTask(id:Int,title : String,desc: String,isDone: Boolean,date: String,category: String){

         CoroutineScope(Dispatchers.Main).launch {

             repo.updateTask(id,title ,desc,isDone,date,category)

         }



    }




}