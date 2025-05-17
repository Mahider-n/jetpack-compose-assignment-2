package com.site7x24learn.jetpackcomposeassignment2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.site7x24learn.jetpackcomposeassignment2.data.local.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<TodoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<TodoEntity>)

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoById(id: Int): Flow<TodoEntity?>
}