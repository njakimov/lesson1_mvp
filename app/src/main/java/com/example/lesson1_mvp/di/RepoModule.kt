package com.example.lesson1_mvp.di

import com.example.lesson1_mvp.cache.IRoomGithubReposCache
import com.example.lesson1_mvp.cache.IRoomGithubUsersCache
import com.example.lesson1_mvp.model.IDataSource
import com.example.lesson1_mvp.model.IGithubRepositoryRepo
import com.example.lesson1_mvp.model.IGithubUsersRepo
import com.example.lesson1_mvp.network.INetworkStatus
import com.example.lesson1_mvp.web.RetrofitGithubRepositoryRepo
import com.example.lesson1_mvp.web.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IRoomGithubUsersCache):
            IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repos(api: IDataSource, networkStatus: INetworkStatus, cache: IRoomGithubReposCache):
            IGithubRepositoryRepo = RetrofitGithubRepositoryRepo(api, networkStatus, cache)
}