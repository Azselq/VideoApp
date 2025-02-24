package com.example.videoapp.domain.usecase

import com.example.videoapp.domain.entity.Video
import com.example.videoapp.domain.repository.CacheVideoRepository
import javax.inject.Inject

class CacheVideosChangeUseCase @Inject constructor(
    private val cacheVideoRepository: CacheVideoRepository
) {
    suspend fun insertVideos(videos: List<Video>) {
        cacheVideoRepository.insertVideos(videos)
    }
    suspend fun clearVideos() {
        cacheVideoRepository.clearVideos()
    }

}