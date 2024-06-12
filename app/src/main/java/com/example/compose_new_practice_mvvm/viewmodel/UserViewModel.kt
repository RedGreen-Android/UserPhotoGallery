package com.example.compose_new_practice_mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_new_practice_mvvm.data.model.User
import com.example.compose_new_practice_mvvm.repository.UserRepository
import com.example.compose_new_practice_mvvm.data.ui.UserState
import com.example.compose_new_practice_mvvm.utils.Constant
import com.example.compose_new_practice_mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _viewState = MutableStateFlow(UserState())
    val viewState = _viewState.asStateFlow()

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser = _selectedUser.asStateFlow()

    init {
        getAllUsers(Constant.ACCESS_KEY)
    }

    private fun getAllUsers(clientId: String) {
        viewModelScope.launch {
            try {
                Log.d("UserViewModel", "Fetching users with client ID: $clientId")
                val users = repository.getUserPhoto(clientId)
                Log.d("UserViewModel", "Fetched users: $users")
                _viewState.update {
//                it.copy(userModel = users, isLoading = false)
                    Log.d("UserViewModel", "Updating view state with fetched users")
                    it.copy(userModel = Resource.success(users), isLoading = false)
                }
            } catch (e: Exception) {
                _viewState.update {
                    it.copy(userModel = Resource.error(null, e.message))
                }
            }
        }
    }

    fun getUserById(userId: String) {
        viewModelScope.launch {
            try {
                Log.d("UserViewModel", "Finding user by ID: $userId")
                val userIds = viewState.value.userModel.data?.map { it.user.id }
                Log.d("UserViewModel", "Available user IDs: $userIds")
                val user = viewState.value.userModel.data?.find { it.user.id == userId }?.user
                _selectedUser.update { user }
                Log.e("UserViewModel", "Got fetching user")
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching user by ID: ${e.message}")
            }
        }
    }
}