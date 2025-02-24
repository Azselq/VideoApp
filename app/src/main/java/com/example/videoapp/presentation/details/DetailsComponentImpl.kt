package com.example.videoapp.presentation.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.example.videoapp.domain.entity.Video
import com.example.videoapp.presentation.extesions.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsComponentImpl @AssistedInject constructor(
    private val detailsStoreFactory: DetailsStoreFactory,
    @Assisted("video") private val video: Video,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("onToggleFullScreen") private val onToggleFullScreen: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext,
) : DetailsComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore { detailsStoreFactory.create(video) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    DetailsStore.Label.ClickBack -> {
                        onBackClicked()
                    }

                    DetailsStore.Label.ToggleFullScreen -> {
                        onToggleFullScreen()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<DetailsStore.State> = store.stateFlow

    override fun onClickBack() {
        store.accept(DetailsStore.Intent.ClickBack)
    }

    override fun onFullScreenToggle() {
        store.accept(DetailsStore.Intent.ToggleFullScreen)
    }

    override fun onPlaybackError(errorMessage: String) {
        store.accept(DetailsStore.Intent.PlaybackError(errorMessage))
    }


    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("video") video: Video,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("onToggleFullScreen") onToggleFullScreen: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext,
        ): DetailsComponentImpl
    }
}