package com.example.videoapp.presentation.root

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.videoapp.domain.entity.Video
import com.example.videoapp.presentation.details.DetailsComponentImpl
import com.example.videoapp.presentation.list.ListComponent
import com.example.videoapp.presentation.list.ListComponentImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.android.parcel.Parcelize

class RootComponentImpl @AssistedInject constructor(
    private val listComponentFactory: ListComponentImpl.Factory,
    private val detailsComponentFactory: DetailsComponentImpl.Factory,
    @Assisted("activity") private val activity: Activity,
    @Assisted("componentContext") componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.List,
        handleBackButton = true,
        childFactory = ::child
    )


    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.List -> {
                val component = listComponentFactory.create(
                    onVideoItemClicked = {
                        navigation.push(Config.Details(it))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.List(component)

            }

            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    video = config.video,
                    onBackClicked = { navigation.pop() },
                    onToggleFullScreen = {toggleFullScreen() },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }
        }


    }
    private fun toggleFullScreen() {
        val currentOrientation = activity.resources.configuration.orientation
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        } else {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }
    sealed interface Config: Parcelable{


        @Parcelize
        data object List: Config

        @Parcelize
        data class Details(val video: Video): Config

    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("activity") activity: Activity,
            @Assisted("componentContext") componentContext: ComponentContext
        ): RootComponentImpl
    }

}