package com.example.videoapp.di

import android.content.Context
import com.example.videoapp.data.local.db.CacheVideoDao
import com.example.videoapp.data.local.db.CacheVideoDatabase
import com.example.videoapp.data.network.api.ApiFactory
import com.example.videoapp.data.network.api.ApiService
import com.example.videoapp.data.repository.CacheVideoRepositoryImpl
import com.example.videoapp.data.repository.NetworkRepositoryImpl
import com.example.videoapp.data.repository.VideoRepositoryImpl
import com.example.videoapp.domain.repository.CacheVideoRepository
import com.example.videoapp.domain.repository.NetworkRepository
import com.example.videoapp.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @[ApplicationScope Binds]
    fun bindVideoRepository(impl: VideoRepositoryImpl): VideoRepository
    @[ApplicationScope Binds]
    fun bindCacheVideoRepository(impl: CacheVideoRepositoryImpl): CacheVideoRepository
    @[ApplicationScope Binds]
    fun bindNetworkRepository(impl: NetworkRepositoryImpl): NetworkRepository


    companion object{
        @[ApplicationScope Provides]
        fun provideApiService(): ApiService = ApiFactory.apiService

        @[ApplicationScope Provides]
        fun provideCacheVideoDatabase(context: Context): CacheVideoDatabase {
            return CacheVideoDatabase.getInstance(context)
        }

        @[ApplicationScope Provides]
        fun provideCacheVideoDao(database: CacheVideoDatabase): CacheVideoDao {
            return  database.cacheVideoDao
        }


    }
}
