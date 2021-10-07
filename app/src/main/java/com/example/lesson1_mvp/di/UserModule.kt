package com.example.lesson1_mvp.di

import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.cache.IRoomGithubUsersCache
import com.example.lesson1_mvp.cache.RoomGithubUsersCache
import com.example.lesson1_mvp.db.Database
import com.example.lesson1_mvp.model.IDataSource
import com.example.lesson1_mvp.model.IGithubUsersRepo
import com.example.lesson1_mvp.network.INetworkStatus
import com.example.lesson1_mvp.presentation.IUserScopeContainer
import com.example.lesson1_mvp.web.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
open class UserModule {
    @Provides
    fun usersCache(database: Database): IRoomGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @UserScope
    @Provides
    open fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoomGithubUsersCache
    ): IGithubUsersRepo {
        return RetrofitGithubUsersRepo(api, networkStatus, cache)
    }

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}