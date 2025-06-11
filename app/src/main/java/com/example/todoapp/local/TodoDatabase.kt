package com.example.todoapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.data.Todo


@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase ()  {

    abstract fun getTaskDao(): TodoDao





}