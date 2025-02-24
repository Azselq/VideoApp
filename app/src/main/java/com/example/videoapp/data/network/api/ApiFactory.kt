package com.example.videoapp.data.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiFactory {
    private const val BASE_URL = "https://api.pexels.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                // Добавляем API-ключ в заголовок запроса
                .addHeader("Authorization", "uYKJfk2tDI4kVgzAxf5n5yeq6gV2mKMkmAy1NX4nfFhqQC9kpEceXxL6")
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}