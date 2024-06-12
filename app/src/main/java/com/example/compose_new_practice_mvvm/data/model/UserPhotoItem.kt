package com.example.compose_new_practice_mvvm.data.model

data class UserPhotoItem(
    val alt_description: String,
    val color: String,
    val created_at: String,
    val description: String,
    val updated_at: String,
    val urls: Urls,
    val user: User,
)