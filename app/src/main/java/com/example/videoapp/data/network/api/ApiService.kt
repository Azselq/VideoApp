package com.example.videoapp.data.network.api

import com.example.videoapp.data.network.dto.PexelsVideoDto
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService {
    @GET("v1/videos/popular")
    suspend fun getVideos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): PexelsVideoDto
}