package com.example.videoapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.videoapp.data.local.model.CacheVideoDbModel


@Database(entities = [CacheVideoDbModel::class], version = 1,exportSchema = false)


abstract class CacheVideoDatabase: RoomDatabase() {

    abstract val cacheVideoDao: CacheVideoDao

    companion object{
        private const val DATABASE_NAME = "videos_db"
        private var INSTANCE: CacheVideoDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): CacheVideoDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = CacheVideoDatabase::class.java,
                    name = DATABASE_NAME
                ).build()

                INSTANCE = database
                return database
            }

        }
    }
}