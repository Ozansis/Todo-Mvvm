package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.local.TodoDao
import com.example.todoapp.local.TodoDatabase
import com.example.todoapp.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : TodoDatabase{

        return Room.databaseBuilder(
            context, TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    fun provideDao(db: TodoDatabase):TodoDao{
        return db.getTaskDao()

    }

    @Provides
    @Singleton
    fun provideRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepository(todoDao)
    }


}