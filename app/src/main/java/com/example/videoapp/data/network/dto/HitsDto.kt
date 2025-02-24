package com.example.videoapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class HitsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("duration") val duration: Int,
    @SerializedName("user") val user: User,
    @SerializedName("video_files") val videoFiles: List<VideosDto>,
    @SerializedName("image") val image: String
    )
