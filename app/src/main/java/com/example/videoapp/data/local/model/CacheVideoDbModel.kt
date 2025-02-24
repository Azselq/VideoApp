package com.example.videoapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "videos")
data class CacheVideoDbModel(
    @PrimaryKey val id: Int,
    val tags: String,
    val duration: Int,
    val url: String,
    val thumbnail: String
)