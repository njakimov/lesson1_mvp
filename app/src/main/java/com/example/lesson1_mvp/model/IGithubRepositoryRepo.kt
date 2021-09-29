package com.example.lesson1_mvp.model

import io.reactivex.rxjava3.core.Single

interface IGithubRepositoryRepo {
    fun getRepos(user: GithubUser): Single<List<GithubRepositoryRepo>>
}