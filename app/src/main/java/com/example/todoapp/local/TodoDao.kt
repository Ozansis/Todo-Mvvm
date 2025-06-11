package com.example.todoapp.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.Todo
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Query("Select * From todo Where isdone= false")
    fun getInProgress() :Flow<List<Todo>>

    @Query("Select * From todo Where isdone= true")
    fun getCompleted() :Flow<List<Todo>>


    @Query("Select * From todo Where  category=:cat")
    fun getCategory(cat: String) :Flow<List<Todo>>

    @Insert
    fun addTodo(todo:Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTask(todo:Todo)




}