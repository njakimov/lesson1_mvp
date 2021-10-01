package com.example.lesson1_mvp

import android.app.Application
import com.example.lesson1_mvp.di.AppComponent
import com.example.lesson1_mvp.di.AppModule
import com.example.lesson1_mvp.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}