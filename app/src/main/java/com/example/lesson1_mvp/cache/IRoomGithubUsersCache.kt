package com.example.lesson1_mvp.cache

import com.example.lesson1_mvp.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomGithubUsersCache {
    fun setCache(users: List<GithubUser>): Completable
    fun getCache(): Single<List<GithubUser>>
}