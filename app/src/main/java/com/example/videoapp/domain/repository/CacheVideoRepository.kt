package com.example.videoapp.domain.repository

import com.example.videoapp.domain.entity.Video
import kotlinx.coroutines.flow.Flow

interface CacheVideoRepository {

    val cacheVideos: Flow<List<Video>>

    suspend fun insertVideos(videos: List<Video>)

    suspend fun clearVideos()
}