package com.example.compose_new_practice_mvvm.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.compose_new_practice_mvvm.data.ui.UserDetailScreen
import com.example.compose_new_practice_mvvm.data.ui.UserListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "user_list"
    ) {
        composable(Navigation.UserList.route) {
            UserListScreen(navController = navController)
        }
        composable(
            route = Navigation.UserDetails.route,
            arguments = listOf(navArgument("userID") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userID")
            userId?.let {
                UserDetailScreen(userId = it)
            }
        }
    }
}

sealed class Navigation (val route: String){

    data object UserList : Navigation("user_list")
    data object UserDetails : Navigation("user_detail/{userID}")
}