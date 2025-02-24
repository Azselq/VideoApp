package com.example.videoapp.domain.repository

import com.example.videoapp.domain.entity.Video


interface VideoRepository {

    suspend fun getVideos(): List<Video>

}