package com.example.videoapp.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    val id: Int,
    val tags: String,
    val duration: Int,
    val url: String,
    val thumbnail: String
): Parcelable
