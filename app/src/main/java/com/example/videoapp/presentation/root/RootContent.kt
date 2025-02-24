package com.example.videoapp.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.example.videoapp.presentation.details.DetailsContent
import com.example.videoapp.presentation.list.ListContent
import com.example.videoapp.presentation.ui.theme.VideoAppTheme


@Composable
fun RootContent(component: RootComponent) {
    VideoAppTheme {
        Children(
            stack = component.stack,
        ){
            when (val instance = it.instance) {
                is RootComponent.Child.List -> {
                    ListContent(component = instance.component)
                }

                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }
            }
        }
    }


}