package com.example.videoapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class VideosDto(
    @SerializedName("id") val id: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("link") val link: String,
)
