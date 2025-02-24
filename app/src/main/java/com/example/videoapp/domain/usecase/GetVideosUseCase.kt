package com.example.videoapp.domain.usecase

import com.example.videoapp.domain.repository.VideoRepository
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val videoRepository: VideoRepository

) {
    suspend operator fun invoke() = videoRepository.getVideos()

}