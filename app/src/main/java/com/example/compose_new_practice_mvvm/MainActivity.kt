package com.example.compose_new_practice_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose_new_practice_mvvm.ui.theme.Compose_new_practice_MVVMTheme
import com.example.compose_new_practice_mvvm.utils.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_new_practice_MVVMTheme {
//                UserListScreen() //no need as we have Nav Graph to take care of it
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}