package com.example.lesson1_mvp.web

import com.example.lesson1_mvp.model.IDataSource
import com.example.lesson1_mvp.model.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getRepos(login: String?) = api.getRepos(login).subscribeOn(Schedulers.io())

}