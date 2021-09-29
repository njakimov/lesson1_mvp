package com.example.lesson1_mvp.web

import com.example.lesson1_mvp.cache.IRoomGithubUsersCache
import com.example.lesson1_mvp.model.IDataSource
import com.example.lesson1_mvp.model.IGithubUsersRepo
import com.example.lesson1_mvp.network.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val userCache: IRoomGithubUsersCache
) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->

        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    userCache.setCache(users).toSingleDefault(users)
                }

        } else {
            userCache.getCache()
        }
    }.subscribeOn(Schedulers.io())
}