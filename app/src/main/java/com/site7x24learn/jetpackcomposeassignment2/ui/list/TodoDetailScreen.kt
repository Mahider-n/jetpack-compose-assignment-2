package com.site7x24learn.jetpackcomposeassignment2.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.site7x24learn.jetpackcomposeassignment2.data.repository.TodoRepository
import com.site7x24learn.jetpackcomposeassignment2.ui.detail.TodoDetailViewModel

@Composable
fun TodoDetailScreen(
    repository: TodoRepository,
    todoId: Int,
    navController: NavController,
    viewModel: TodoDetailViewModel = viewModel(factory = TodoViewModelFactory(repository,todoId))
) {
    val todo by viewModel.todo.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            todo?.let {
                Text("ID: ${it.id}", style = MaterialTheme.typography.titleLarge)
                Text("User ID: ${it.userId}", style = MaterialTheme.typography.titleLarge)
                Text("Title: ${it.title}", style = MaterialTheme.typography.titleLarge)
                Text(
                    "Status: ${if (it.completed) "Completed" else "Pending"}",
                    style = MaterialTheme.typography.titleLarge,
                    color = if (it.completed) Color.Green else Color.Red
                )
            } ?: Text("Loading...")
        }
    }
}