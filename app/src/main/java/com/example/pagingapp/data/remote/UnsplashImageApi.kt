package com.example.pagingapp.data.remote

import com.example.pagingapp.model.UnsplashImageData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashImageApi {

    @Headers("Authorization: Client-ID ")
    @GET
    suspend fun getAllImages(
        @Query("page") page:Int,
        @Query("per_page") perPage:Int
    ):List<UnsplashImageData>

}