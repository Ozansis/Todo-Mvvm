package com.example.todoapp.repository

import com.example.todoapp.data.Todo
import com.example.todoapp.local.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TodoRepository @Inject constructor(var todoDao: TodoDao) {


     fun loadAllTask() : Flow<List<Todo>>  {
        return todoDao.getInProgress()

    }


    fun loadCompleted() :Flow<List<Todo>>{
        return todoDao.getCompleted()

    }


    suspend fun deletetask(task: Todo)=withContext(Dispatchers.IO){
        todoDao.deleteTask(task)
    }

    suspend fun addTask(title : String,desc: String,isDone: Boolean,date: String,category: String)
    = withContext(Dispatchers.IO){
        val newTask = Todo(0,title,desc,isDone,date,category)
        todoDao.addTodo(newTask)
    }

    suspend fun updateTask(id:Int,title : String,desc: String,isDone: Boolean,date: String,category: String)
    =withContext(Dispatchers.IO) {
        val updateTodo = Todo(id,title,desc,isDone,date,category)
        todoDao.updateTodo(updateTodo)


    }

    suspend fun updateTask(todo: Todo)=withContext(Dispatchers.IO){
        todoDao.updateTodo(todo)

    }

     fun getCategory(category: String) : Flow<List<Todo>>{

        return todoDao.getCategory(category)
    }










}