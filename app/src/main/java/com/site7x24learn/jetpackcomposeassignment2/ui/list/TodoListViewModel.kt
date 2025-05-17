package com.site7x24learn.jetpackcomposeassignment2.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.site7x24learn.jetpackcomposeassignment2.data.local.entity.TodoEntity
import com.site7x24learn.jetpackcomposeassignment2.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todos = MutableStateFlow<List<TodoEntity>>(emptyList())
    val todos: StateFlow<List<TodoEntity>> = _todos

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {

        viewModelScope.launch {
            repository.todos.collect {
                _todos.value = it
            }
        }
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                repository.refreshTodos()
            } catch (e: Exception) {
                 errorMessage = when (e) {
                    is UnknownHostException -> "No internet connection"
                    is SocketTimeoutException -> "Request timed out"
                    else -> "Failed to load data: ${e.message}"
                }
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }

    }
}

