package com.example.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="uid") val id : Int=0,
    @ColumnInfo(name= "title") val title: String?,
    @ColumnInfo(name="desc") val description : String?,
    @ColumnInfo(name= "isdone") val isDone : Boolean?,
    @ColumnInfo(name = "date") val date : String="",
    @ColumnInfo(name="category") val category: String


): Serializable {
}
