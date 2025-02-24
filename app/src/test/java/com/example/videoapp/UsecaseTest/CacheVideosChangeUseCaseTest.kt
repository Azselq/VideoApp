package com.example.videoapp.UsecaseTest

import com.example.videoapp.domain.entity.Video
import com.example.videoapp.domain.repository.CacheVideoRepository
import com.example.videoapp.domain.usecase.CacheVideosChangeUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CacheVideosChangeUseCaseTest {

    private lateinit var cacheVideoRepository: CacheVideoRepository
    private lateinit var cacheVideosChangeUseCase: CacheVideosChangeUseCase

    @Before
    fun setUp() {
        cacheVideoRepository = mock()
        cacheVideosChangeUseCase = CacheVideosChangeUseCase(cacheVideoRepository)
    }

    @Test
    fun `insertVideos should call insertVideos on repository`() = runBlocking {
        val expectedVideos = listOf(
            Video(
                id = 1,
                thumbnail = "https://cdn.pixabay.com/video/2024/12/08/245661_large.jpg",
                duration = 120,
                tags = "happy new year 2025, happy new year, new year, 2025",
                url =  "https://cdn.pixabay.com/video/2025/02/17/258862_medium.mp4"
            ),
            Video(
                id = 2,
                thumbnail = "https://cdn.pixabay.com/video/2024/12/20/247886_large.jpg",
                duration = 90,
                tags = "tag2, tag3, tag4",
                url = "https://cdn.pixabay.com/video/2024/12/20/247886_medium.mp4"
            )
        )
        cacheVideosChangeUseCase.insertVideos(expectedVideos)
        verify(cacheVideoRepository).insertVideos(expectedVideos)
    }

    @Test
    fun `clearVideos should call clearVideos on repository`() = runBlocking {
        cacheVideosChangeUseCase.clearVideos()
        verify(cacheVideoRepository).clearVideos()
    }
}
