package com.example.lesson1_mvp.web

import com.example.lesson1_mvp.cache.IRoomGithubReposCache
import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.model.IDataSource
import com.example.lesson1_mvp.model.IGithubRepositoryRepo
import com.example.lesson1_mvp.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoryRepo(
    val api: IDataSource, val networkStatus: INetworkStatus, val reposCache: IRoomGithubReposCache
) : IGithubRepositoryRepo {
    override fun getRepos(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.login?.let { url ->
                    api.getRepos(url)
                        .flatMap { repositories ->
                            reposCache.setCache(user, repositories).toSingleDefault(repositories)
                        }
                } ?: Single.error<List<GithubRepositoryRepo>>(
                    RuntimeException("User has no repos url ")
                ).subscribeOn(Schedulers.io())
            } else {
                reposCache.getCache(user)
            }
        }.subscribeOn(Schedulers.io())
}