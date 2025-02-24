package com.example.videoapp.presentation.list

import com.example.videoapp.domain.entity.Video
import kotlinx.coroutines.flow.StateFlow

interface ListComponent {
    val model: StateFlow<ListStore.State>

    fun onVideoItemCLick(video: Video)

    fun onRefresh()
}