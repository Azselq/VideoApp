package com.example.videoapp.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.videoapp.presentation.details.DetailsComponent
import com.example.videoapp.presentation.list.ListComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>
    sealed interface Child{
        data class List(val component: ListComponent): Child

        data class Details(val component: DetailsComponent): Child

    }
}