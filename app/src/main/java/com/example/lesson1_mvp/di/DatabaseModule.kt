package com.example.lesson1_mvp.di

import androidx.room.Room
import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, DB_NAME).build()

    companion object {
        private const val DB_NAME = "database.db"

    }
}