package com.site7x24learn.jetpackcomposeassignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.site7x24learn.jetpackcomposeassignment2.data.local.AppDatabase
import com.site7x24learn.jetpackcomposeassignment2.data.remote.RetrofitClient
import com.site7x24learn.jetpackcomposeassignment2.data.repository.TodoRepository
import com.site7x24learn.jetpackcomposeassignment2.navigation.NavGraph
import com.site7x24learn.jetpackcomposeassignment2.ui.theme.Jetpackcomposeassignment2Theme

class MainActivity : ComponentActivity() {
    private val database by lazy { AppDatabase.getInstance(this) }
    private val repository by lazy {
        TodoRepository(
            RetrofitClient.apiService,
            database.todoDao()
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpackcomposeassignment2Theme {
                NavGraph(repository = repository)

            }
        }
    }
}

