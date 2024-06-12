package com.example.compose_new_practice_mvvm.data.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compose_new_practice_mvvm.utils.Status
import com.example.compose_new_practice_mvvm.viewmodel.UserViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserListScreen(viewModel: UserViewModel = hiltViewModel(),
                   navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val state by viewModel.viewState.collectAsState()
    Surface(
        color = androidx.compose.material3.MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            when (state.userModel.status) {
                Status.LOADING -> {
                    Text("Loading...") //and have spinner
                }
                Status.SUCCESS -> {
                    state.userModel.data?.let { users ->
                        LazyColumn(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            items(users) { user ->
                                UserListItem(userDataItem = user) {
                                    navController.navigate("user_detail/${user.user.id}")
                                }
                            }
                        }
                    }
                }
                Status.ERROR -> {
                    Text("Error: ${state.userModel.message}") //or show Toast
                }
            }
            /**Below code is showing before "Resource" utils is added using
             * state directly. Off course better to encapsulate in Resource to handel success, error, loading
             */
//            if (state.isLoading) {
//                Text("Loading...")
//            } else {
//                val users = state.userModel ?: emptyList()
//                if (users.isNotEmpty()) {
//                    LazyColumn(
//                        modifier = Modifier.padding(10.dp)
//                    ) {
//                        items(users) { user ->
//                            UserListItem(user)
//                        }
//                    }
//                } else {
//                    Text("No data available")
//                }
//            }
            // Trigger the call to fetch users when the composable enters the composition, not need in this case!
            LaunchedEffect(Unit) {
//                viewModel.getAllUsers() //no Need since I have do it in init block in Viewmodel
            }
        }
    }
}

/**
 * Not using this composable for the first List screen but demonstrating a
 * another simple way with Scaffold and less code
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserListScreen2(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    val state by viewModel.viewState.collectAsState()

    Scaffold {
        when (state.userModel.status) {
            Status.LOADING -> {
                // Loading UI - I would add Spinner in this part
            }
            Status.SUCCESS -> {
                state.userModel.data?.let { users ->
                    LazyColumn(modifier = Modifier.padding(10.dp)) {
                        items(users) { user ->
                            UserListItem(userDataItem = user) {
                                navController.navigate("user_detail/${user.user.id}")
                            }
                        }
                    }
                }
            }
            Status.ERROR -> {
                // Error UI - I would add Toast here
            }
        }
    }
}