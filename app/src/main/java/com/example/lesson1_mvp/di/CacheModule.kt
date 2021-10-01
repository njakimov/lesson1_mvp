package com.example.lesson1_mvp.di

import androidx.room.Room
import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.cache.IRoomGithubReposCache
import com.example.lesson1_mvp.cache.IRoomGithubUsersCache
import com.example.lesson1_mvp.cache.RoomGithubReposCache
import com.example.lesson1_mvp.cache.RoomGithubUsersCache
import com.example.lesson1_mvp.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(
        app,
        Database::class.java, DB_NAME
    )
        .build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IRoomGithubUsersCache =
        RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repos(database: Database): IRoomGithubReposCache =
        RoomGithubReposCache(database)

    companion object {

        private const val DB_NAME = "database.db"

    }
}