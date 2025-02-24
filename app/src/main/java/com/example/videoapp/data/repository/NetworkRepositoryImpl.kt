package com.example.videoapp.data.repository

import com.example.videoapp.data.mapper.toEntity
import com.example.videoapp.data.network.api.ApiService
import com.example.videoapp.domain.entity.Video
import com.example.videoapp.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): NetworkRepository {
    override suspend fun getVideos(): List<Video>{
        return apiService.getVideos().toEntity()
    }
}