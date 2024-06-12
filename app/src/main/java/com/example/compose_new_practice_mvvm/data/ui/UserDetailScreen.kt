package com.example.compose_new_practice_mvvm.data.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.compose_new_practice_mvvm.viewmodel.UserViewModel


@Composable
fun UserDetailScreen(userId: String, viewModel: UserViewModel = hiltViewModel()) {
    Log.d("UserDetailScreen", "User ID: $userId")

    LaunchedEffect(userId) {
        Log.d("UserDetailScreen", "LaunchedEffect triggered")
        viewModel.getUserById(userId)
    }

    val user by viewModel.viewState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.CenterStart
    ) {
        user.userModel.data?.let { userInfo ->
            AsyncImage(
                alpha = 0.5f,
                model = userInfo[0].user.profile_image.large,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterStart)
            ) {
                val offset = Offset(5.0f, 10.0f)
                Text(
                    text = "Name: ${userInfo[0].user.name}",
                    style = TextStyle(
                        fontSize = 30.sp,
                        shadow = Shadow(
                            color = androidx.compose.ui.graphics.Color.DarkGray,
                            offset = offset,
                            blurRadius = 3f
                        )
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "About me: ${userInfo[0].user.bio}",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Total photos: ${userInfo[0].user.total_photos}",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Total collection: ${userInfo[0].user.total_collections}",
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Total likes: ${userInfo[0].user.total_likes}",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Location: ${userInfo[0].user.location}",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Instagram: ${userInfo[0].user.instagram_username ?: "not provided"}",
                    fontStyle = FontStyle.Italic
                )
            }
        }
//        } ?: run {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//        }
    }
}

