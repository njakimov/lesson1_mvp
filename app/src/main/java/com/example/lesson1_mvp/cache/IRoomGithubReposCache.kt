package com.example.lesson1_mvp.cache

import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomGithubReposCache {
    fun setCache(user: GithubUser, repositories: List<GithubRepositoryRepo>): Completable
    fun getCache(user: GithubUser): Single<List<GithubRepositoryRepo>>
}