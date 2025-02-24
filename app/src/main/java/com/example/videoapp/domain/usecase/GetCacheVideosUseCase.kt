package com.example.videoapp.domain.usecase

import com.example.videoapp.domain.repository.CacheVideoRepository
import javax.inject.Inject

class GetCacheVideosUseCase @Inject constructor(
    private val cacheVideoRepository: CacheVideoRepository
) {
    operator fun invoke() = cacheVideoRepository.cacheVideos
}