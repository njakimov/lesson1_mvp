package com.example.lesson1_mvp.di

import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.cache.IRoomGithubReposCache
import com.example.lesson1_mvp.cache.RoomGithubReposCache
import com.example.lesson1_mvp.db.Database
import com.example.lesson1_mvp.model.IDataSource
import com.example.lesson1_mvp.model.IGithubRepositoryRepo
import com.example.lesson1_mvp.network.INetworkStatus
import com.example.lesson1_mvp.presentation.IRepositoryScopeContainer
import com.example.lesson1_mvp.web.RetrofitGithubRepositoryRepo
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {
    @Provides
    fun repositoriesCache(database: Database): IRoomGithubReposCache {
        return RoomGithubReposCache(database)
    }

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRoomGithubReposCache
    ): IGithubRepositoryRepo {
        return RetrofitGithubRepositoryRepo(api, networkStatus, cache)
    }

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}
