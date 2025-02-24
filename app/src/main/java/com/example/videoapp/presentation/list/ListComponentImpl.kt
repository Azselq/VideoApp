package com.example.videoapp.presentation.list

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

class ListComponentImpl @AssistedInject constructor(
    private val listStoreFactory: ListStoreFactory,
    @Assisted("onVideoItemClicked") private val onVideoItemClicked: (Video) -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext,
) : ListComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore { listStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect{
                when(it){
                    is ListStore.Label.VideoItemClicked -> {
                        onVideoItemClicked(it.video)
                    }
                }
            }
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<ListStore.State> = store.stateFlow

    override fun onVideoItemCLick(video: Video) {
       store.accept(ListStore.Intent.VideoItemClicked(video))
    }

    override fun onRefresh() {
        store.accept(ListStore.Intent.RefreshList)
    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("onVideoItemClicked") onVideoItemClicked: (Video) -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext,
        ): ListComponentImpl
    }
}