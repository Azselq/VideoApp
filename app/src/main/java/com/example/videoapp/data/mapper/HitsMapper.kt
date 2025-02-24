package com.example.videoapp.data.mapper

import com.example.videoapp.data.network.dto.HitsDto
import com.example.videoapp.data.network.dto.PexelsVideoDto
import com.example.videoapp.data.network.dto.User
import com.example.videoapp.data.network.dto.VideosDto
import com.example.videoapp.domain.entity.Video
import kotlin.math.abs




fun List<VideosDto>.bestVariant(targetWidth: Int = 1920, targetHeight: Int = 1080): VideosDto {
    return this.minByOrNull { variant ->
        abs(variant.width - targetWidth) + abs(variant.height - targetHeight)
    }!!
}

fun HitsDto.toEntity(): Video {
    val bestVideo = videoFiles.bestVariant()
    return Video(
        id = id,
        tags = user.name,
        duration = duration,
        url = bestVideo.link,
        thumbnail = image
    )
}

fun User.toEntity(): String {
    return name
}

fun PexelsVideoDto.toEntity(): List<Video> {
    return videos.map { it.toEntity() }
}
