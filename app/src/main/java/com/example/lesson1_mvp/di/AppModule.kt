package com.example.lesson1_mvp.di

import com.example.lesson1_mvp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}