package com.example.lesson1_mvp

import android.app.Application
import com.example.lesson1_mvp.di.*
import com.example.lesson1_mvp.presentation.IRepositoryScopeContainer
import com.example.lesson1_mvp.presentation.IUserScopeContainer

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {

    lateinit var appComponent: AppComponent

    var userSubcomponent: UserSubcomponent? = null
        private set
    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() =
        userSubcomponent?.repositorySubcomponent().also {
            repositorySubcomponent = it
        }

    override fun releaseUserScope() {
        userSubcomponent = null
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    companion object {
        lateinit var instance: App
    }
}