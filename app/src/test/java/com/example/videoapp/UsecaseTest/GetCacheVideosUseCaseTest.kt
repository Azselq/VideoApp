package com.example.videoapp.UsecaseTest
import com.example.videoapp.domain.entity.Video
import com.example.videoapp.domain.repository.CacheVideoRepository
import com.example.videoapp.domain.usecase.GetCacheVideosUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCacheVideosUseCaseTest {

    private lateinit var cacheVideoRepository: CacheVideoRepository
    private lateinit var getCacheVideosUseCase: GetCacheVideosUseCase

    @Before
    fun setUp() {
        cacheVideoRepository = mock()
        getCacheVideosUseCase = GetCacheVideosUseCase(cacheVideoRepository)
    }

    @Test
    fun `invoke should return cached videos from repository`() = runBlocking {
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
        val expectedFlow = flowOf(expectedVideos)

        whenever(cacheVideoRepository.cacheVideos).thenReturn(expectedFlow)

        val result = getCacheVideosUseCase.invoke()

        assertEquals(expectedFlow, result)
    }
}
