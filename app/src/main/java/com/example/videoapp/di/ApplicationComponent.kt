package com.example.videoapp.di

import android.content.Context
import com.example.videoapp.MainActivity
import dagger.BindsInstance
import dagger.Component
@ApplicationScope
@Component  (
    modules = [
        DataModule::class,
        PresentationModule::class
    ]
)

interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}