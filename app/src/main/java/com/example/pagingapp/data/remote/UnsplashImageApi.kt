package com.example.pagingapp.data.remote

import android.os.Build
import com.example.pagingapp.BuildConfig
import com.example.pagingapp.model.UnsplashImageData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashImageApi {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page:Int,
        @Query("per_page") perPage:Int
    ):List<UnsplashImageData>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("page") page:Int,
        @Query("per_page") perPage:Int
    ):List<UnsplashImageData>



}