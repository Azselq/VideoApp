package com.example.videoapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") val name: String,
)