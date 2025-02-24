package com.example.videoapp.UsecaseTest


import com.example.videoapp.domain.entity.Video
import com.example.videoapp.domain.repository.VideoRepository
import com.example.videoapp.domain.usecase.GetVideosUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetVideosUseCaseTest {

    private lateinit var videoRepository: VideoRepository
    private lateinit var getVideosUseCase: GetVideosUseCase

    @Before
    fun setUp() {
        videoRepository = mock()
        getVideosUseCase = GetVideosUseCase(videoRepository)
    }

    @Test
    fun `invoke should call getVideos on repository and return result`() = runBlocking {
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
        whenever(videoRepository.getVideos()).thenReturn(expectedVideos)

        val result = getVideosUseCase.invoke()

        verify(videoRepository).getVideos()
        assertEquals(expectedVideos, result)
    }
}
