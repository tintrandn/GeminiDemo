package com.example.aitripdemo.service

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {
    @Headers("Accept-Version: v1")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int = 1,
        @Query("client_id") apiKey: String
    ): UnsplashResponse
}
