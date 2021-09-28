package com.example.lesson1_mvp.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String?): Single<List<GithubRepositoryRepo>>
}