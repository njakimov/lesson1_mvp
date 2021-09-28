package com.example.lesson1_mvp.view

import com.example.lesson1_mvp.model.GithubRepositoryRepo

interface UserItemView : IItemView {

    fun showLogin(login: String)
    fun loadAvatar(url: String)
    fun loadRepos(repo: GithubRepositoryRepo)

}