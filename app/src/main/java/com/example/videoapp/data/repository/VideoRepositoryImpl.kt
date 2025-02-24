package com.example.videoapp.data.repository


import com.example.videoapp.domain.entity.Video
import com.example.videoapp.domain.repository.CacheVideoRepository
import com.example.videoapp.domain.repository.NetworkRepository
import com.example.videoapp.domain.repository.VideoRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val cacheVideoRepository: CacheVideoRepository
) : VideoRepository {

    override suspend fun getVideos(): List<Video> {
        return try {
            val networkVideos = networkRepository.getVideos()
            cacheVideoRepository.clearVideos()
            cacheVideoRepository.insertVideos(networkVideos)
            networkVideos
        } catch (e: Exception) {
            return cacheVideoRepository.cacheVideos.first()
        }
    }
}
