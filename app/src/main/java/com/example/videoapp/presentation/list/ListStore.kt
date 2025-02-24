package com.example.videoapp.presentation.list

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.videoapp.domain.entity.Video
import com.example.videoapp.domain.usecase.GetVideosUseCase
import com.example.videoapp.presentation.list.ListStore.Intent
import com.example.videoapp.presentation.list.ListStore.Label
import com.example.videoapp.presentation.list.ListStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ListStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data class VideoItemClicked(val video: Video) : Intent

        data object RefreshList : Intent
    }

    data class State(
        val listState: ListState,
        val videos: List<Video>
    ) {

        sealed interface ListState {

            data object Initial : ListState

            data object Loading : ListState

            data object Error : ListState

            data class Loaded(val videos: List<Video>) : ListState

        }
    }

    sealed interface Label {
        data class VideoItemClicked(val video: Video) : Label
    }
}

class ListStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getVideosUseCase: GetVideosUseCase
) {

    fun create(): ListStore =
        object : ListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ListStore",
            initialState = State(State.ListState.Initial, listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object LoadList : Action


    }

    private sealed interface Msg {

        data object LoadList : Msg

        data object LoadingListError : Msg

        data class ListLoaded(val videos: List<Video>) : Msg


    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.LoadList)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.VideoItemClicked -> {
                    publish(Label.VideoItemClicked(intent.video))
                }
                Intent.RefreshList -> {
                    loadVideos()
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.LoadList -> {
                    loadVideos()
                }

            }
        }

        private fun loadVideos() {
            dispatch(Msg.LoadList)
            scope.launch {
                try {
                    val videos = getVideosUseCase()
                    dispatch(Msg.ListLoaded(videos))
                } catch (e: Exception) {
                    dispatch(Msg.LoadingListError)

                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.ListLoaded -> copy(listState = State.ListState.Loaded(msg.videos))
                Msg.LoadList -> copy(listState = State.ListState.Loading)
                Msg.LoadingListError -> copy(listState = State.ListState.Error)
            }
    }
}
