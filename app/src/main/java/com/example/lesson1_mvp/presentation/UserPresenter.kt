package com.example.lesson1_mvp.presentation

import com.example.lesson1_mvp.model.GithubUsersRepo
import com.example.lesson1_mvp.view.ui.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router,
    val currentUserIdx: Int
) : MvpPresenter<UserView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun loadData() {
        val user = usersRepo.getUser(currentUserIdx)
        viewState.updateUser(user)
    }
}