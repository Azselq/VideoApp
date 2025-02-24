package com.example.videoapp.presentation.details

import kotlinx.coroutines.flow.StateFlow

interface DetailsComponent {

    val model: StateFlow<DetailsStore.State>

    fun onClickBack()

    fun onFullScreenToggle()

    fun onPlaybackError(errorMessage: String)
}