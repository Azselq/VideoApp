package com.example.videoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.example.videoapp.presentation.root.RootComponentImpl
import com.example.videoapp.presentation.root.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: RootComponentImpl.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as VideoApp).applicationComponent.inject(this)
        val activity = this
        super.onCreate(savedInstanceState)
        setContent {
            RootContent(component =rootComponentFactory.create(activity = activity,defaultComponentContext()))
        }
    }
}
