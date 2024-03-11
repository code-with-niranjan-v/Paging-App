package com.example.pagingapp.ui.data.remote

import com.example.pagingapp.BuildConfig.API_KEY
import com.example.pagingapp.ui.model.User
import com.example.pagingapp.ui.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface UserApi {

    @Headers("app-id: ${API_KEY}")
    @GET("user")
    suspend fun getUser(
        @Query("page")
        page:Int,
        @Query("limit")
        limit:Int
    ):UserResponse

}