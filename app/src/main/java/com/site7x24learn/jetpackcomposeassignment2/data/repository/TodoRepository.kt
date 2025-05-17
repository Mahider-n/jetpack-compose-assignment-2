package com.site7x24learn.jetpackcomposeassignment2.data.repository

import com.site7x24learn.jetpackcomposeassignment2.data.local.entity.toEntity
import kotlinx.coroutines.flow.Flow
import com.site7x24learn.jetpackcomposeassignment2.data.local.dao.TodoDao
import com.site7x24learn.jetpackcomposeassignment2.data.local.entity.TodoEntity
import com.site7x24learn.jetpackcomposeassignment2.data.remote.TodoApiService


class TodoRepository(
    private val apiService: TodoApiService,
    private val todoDao: TodoDao
) {
    val todos: Flow<List<TodoEntity>> = todoDao.getAllTodos()

    suspend fun refreshTodos() {

        val remoteTodos = apiService.getTodos()
        todoDao.insertTodos(remoteTodos.map { it.toEntity() })

    }

    fun getTodoById(id: Int): Flow<TodoEntity?> = todoDao.getTodoById(id)
}