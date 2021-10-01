package com.example.lesson1_mvp.screens

import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.view.ui.RepoFragment
import com.example.lesson1_mvp.view.ui.UserFragment
import com.example.lesson1_mvp.view.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
    fun repo(repo: GithubRepositoryRepo) = FragmentScreen { RepoFragment(repo) }
}