package com.example.compose_new_practice_mvvm.data.ui

import com.example.compose_new_practice_mvvm.data.model.UserPhotoItem
import com.example.compose_new_practice_mvvm.utils.Resource

data class UserState(
    val isLoading: Boolean = true,
    val userModel: Resource<List<UserPhotoItem>> = Resource.loading(null)
//    val errorMsg: String? = null //error state can be added
)