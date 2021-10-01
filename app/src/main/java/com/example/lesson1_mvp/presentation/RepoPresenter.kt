package com.example.lesson1_mvp.presentation

import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.view.ui.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepoPresenter(var currentRepo: GithubRepositoryRepo) :
    MvpPresenter<UserView>() {

    @Inject
    lateinit var router: Router


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        updateRepo()
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun updateRepo() {
        viewState.updateRepo(currentRepo)
    }

}