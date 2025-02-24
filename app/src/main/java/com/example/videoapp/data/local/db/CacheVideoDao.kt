package com.example.videoapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.videoapp.data.local.model.CacheVideoDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheVideoDao{

    @Query("SELECT * FROM videos")
    fun getCacheVideos(): Flow<List<CacheVideoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<CacheVideoDbModel>)

    @Query("DELETE FROM videos")
    suspend fun clearVideos()

}