package com.example.videoapp.presentation.details

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.videoapp.domain.entity.Video
import com.example.videoapp.presentation.details.DetailsStore.Intent
import com.example.videoapp.presentation.details.DetailsStore.Label
import com.example.videoapp.presentation.details.DetailsStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface DetailsStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickBack: Intent
        data object ToggleFullScreen: Intent
        data class PlaybackError(val errorMessage: String) : Intent
    }

    data class State(
        val detailsState: DetailsState,
        val video: Video
    ){
        sealed interface DetailsState{
            data object Initial : DetailsState

            data object Loading : DetailsState

            data object Error : DetailsState

            data object Loaded : DetailsState

        }
    }

    sealed interface Label {
        data object ClickBack: Label
        data object ToggleFullScreen: Label
    }
}

class DetailsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory
) {

    fun create(video: Video): DetailsStore =
        object : DetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "DetailsStore",
            initialState = State(
                detailsState = State.DetailsState.Loaded,
                video = video
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object DetailsLoading : Action
        data object DetailsLoadingError: Action
    }

    private sealed interface Msg {
        data object DetailsLoading : Msg
        data object DetailsLoadingError: Msg
    }

    private inner class BootstrapperImpl(
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }

                Intent.ToggleFullScreen -> {
                    publish(Label.ToggleFullScreen)
                }

                is Intent.PlaybackError -> dispatch(Msg.DetailsLoadingError)
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
          when (action) {
                Action.DetailsLoading -> {
                    dispatch(Msg.DetailsLoading)
                }

                Action.DetailsLoadingError -> {
                    dispatch(Msg.DetailsLoadingError)
                }

            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                Msg.DetailsLoading -> {
                    copy(detailsState = State.DetailsState.Loading)
                }
                Msg.DetailsLoadingError -> {
                    copy(detailsState = State.DetailsState.Error)
                }

            }
    }
}

