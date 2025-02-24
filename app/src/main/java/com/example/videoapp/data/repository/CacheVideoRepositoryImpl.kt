package com.example.videoapp.data.repository

import com.example.videoapp.data.local.db.CacheVideoDao
import com.example.videoapp.data.mapper.toDbModel
import com.example.videoapp.data.mapper.toEntities
import com.example.videoapp.domain.entity.Video
import com.example.videoapp.domain.repository.CacheVideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheVideoRepositoryImpl @Inject constructor(
    private val cacheVideoDao: CacheVideoDao
) : CacheVideoRepository {
    override val cacheVideos: Flow<List<Video>> = cacheVideoDao.getCacheVideos()
        .map { it.toEntities() }

    override suspend fun insertVideos(videos: List<Video>) {
        cacheVideoDao.insertVideos(videos.map { it.toDbModel() })
    }

    override suspend fun clearVideos() {
        cacheVideoDao.clearVideos()
    }
}