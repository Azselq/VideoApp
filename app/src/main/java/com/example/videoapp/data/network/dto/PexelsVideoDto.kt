package com.example.videoapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class PexelsVideoDto(
    @SerializedName("videos") val videos: List<HitsDto>,
)
