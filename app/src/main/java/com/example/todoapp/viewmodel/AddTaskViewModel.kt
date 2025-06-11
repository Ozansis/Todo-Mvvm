package com.example.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTaskViewModel @Inject constructor(var repo : TodoRepository): ViewModel() {



    fun addTask(title : String,desc: String,isDone: Boolean,date: String,category: String){

        CoroutineScope(Dispatchers.Main).launch {
            repo.addTask(title,desc,isDone,date,category)

        }


    }





}