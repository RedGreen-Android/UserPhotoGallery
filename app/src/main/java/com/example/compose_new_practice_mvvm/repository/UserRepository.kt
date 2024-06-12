package com.example.compose_new_practice_mvvm.repository

import com.example.compose_new_practice_mvvm.data.remote.UserAPI
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserAPI) {
    suspend fun getUserPhoto(clientId: String) = api.getUserPhoto(clientId)
}