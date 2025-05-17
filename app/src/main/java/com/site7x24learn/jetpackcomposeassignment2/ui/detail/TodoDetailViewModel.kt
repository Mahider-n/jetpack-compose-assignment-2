package com.site7x24learn.jetpackcomposeassignment2.ui.detail

import androidx.lifecycle.ViewModel
import com.site7x24learn.jetpackcomposeassignment2.data.local.entity.TodoEntity
import com.site7x24learn.jetpackcomposeassignment2.data.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoDetailViewModel(
    private val repository: TodoRepository,
    private val todoId: Int
) : ViewModel() {
    val todo: Flow<TodoEntity?> = repository.getTodoById(todoId)
}