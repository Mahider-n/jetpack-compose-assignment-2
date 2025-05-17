package com.site7x24learn.jetpackcomposeassignment2.ui.list


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.site7x24learn.jetpackcomposeassignment2.data.repository.TodoRepository
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import com.site7x24learn.jetpackcomposeassignment2.ui.component.TodoItem
import com.site7x24learn.jetpackcomposeassignment2.ui.detail.TodoDetailViewModel
import kotlinx.coroutines.delay
@Composable
fun TodoListScreen(

    navController: NavController,
    repository: TodoRepository
) {
    val viewModel: TodoListViewModel = viewModel(
        factory = TodoViewModelFactory(repository)
    )

    val todos by viewModel.todos.collectAsState(initial = emptyList())
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage



    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        LaunchedEffect(errorMessage) {
            if (errorMessage != null) {
                delay(30000L)
                viewModel.loadData()
            }
        }

        Text(
            text = "Todo List App",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(bottom = 16.dp,top=16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)

        )

        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(8.dp))
        }
        errorMessage?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Button(onClick = { viewModel.loadData() }) {
                    Text("Retry Request")
                }
            }
        }

        LazyColumn {
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onClick = { navController.navigate("detail/${todo.id}") }
                )
            }
        }
    }
}
class TodoViewModelFactory(
    private val repository: TodoRepository,
    private val todoId: Int = 0
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TodoListViewModel::class.java) -> {
                TodoListViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TodoDetailViewModel::class.java) -> {
                TodoDetailViewModel(repository, todoId) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
