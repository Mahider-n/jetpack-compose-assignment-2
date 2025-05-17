package com.site7x24learn.jetpackcomposeassignment2.data.remote

import com.site7x24learn.jetpackcomposeassignment2.data.local.entity.TodoEntity
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<TodoEntity>
}