package com.example.videoapp.domain.repository

import com.example.videoapp.domain.entity.Video

interface NetworkRepository {
    suspend fun getVideos(): List<Video>
}