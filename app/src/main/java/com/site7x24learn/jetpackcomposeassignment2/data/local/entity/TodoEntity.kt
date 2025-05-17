package com.site7x24learn.jetpackcomposeassignment2.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)

//to change the API to room entity
fun TodoEntity.toEntity(): TodoEntity {
    return TodoEntity(id, userId, title, completed)
}