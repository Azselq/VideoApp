package com.example.videoapp

import android.app.Application
import com.example.videoapp.di.ApplicationComponent
import com.example.videoapp.di.DaggerApplicationComponent

class VideoApp: Application()  {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }

}