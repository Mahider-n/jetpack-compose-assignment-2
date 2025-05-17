package com.site7x24learn.jetpackcomposeassignment2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.site7x24learn.jetpackcomposeassignment2.data.repository.TodoRepository
import com.site7x24learn.jetpackcomposeassignment2.ui.list.TodoDetailScreen
import com.site7x24learn.jetpackcomposeassignment2.ui.list.TodoListScreen

@Composable
fun NavGraph(
    repository: TodoRepository,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(
                navController = navController,
                repository = repository // Pass repository here
            )
        }
        composable("detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: 0
            TodoDetailScreen(todoId = todoId, repository = repository, navController = navController)
        }

    }
}