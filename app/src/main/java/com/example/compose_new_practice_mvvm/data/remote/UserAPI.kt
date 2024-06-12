package com.example.compose_new_practice_mvvm.data.remote

import com.example.compose_new_practice_mvvm.data.model.UserPhotoItem
import com.example.compose_new_practice_mvvm.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {
    //https://api.unsplash.com/photos?client_id=CJ77ORS_eVY1hBh9RSFqEZ0AcVJJXifDbqTqnGO8zO0
    @GET(Constant.API_ENDPOINT)
    suspend fun getUserPhoto(@Query("client_id") clientId: String) : List<UserPhotoItem>

}