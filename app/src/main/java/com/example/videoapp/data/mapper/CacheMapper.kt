package com.example.videoapp.data.mapper

import com.example.videoapp.data.local.model.CacheVideoDbModel
import com.example.videoapp.domain.entity.Video


fun Video.toDbModel(): CacheVideoDbModel = CacheVideoDbModel(id,tags,duration,url,thumbnail)

fun CacheVideoDbModel.toEntity(): Video = Video(id,tags,duration,url,thumbnail)

fun List<CacheVideoDbModel>.toEntities(): List<Video> = map { it.toEntity() }