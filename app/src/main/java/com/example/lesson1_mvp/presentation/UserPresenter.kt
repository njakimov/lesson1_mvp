package com.example.lesson1_mvp.presentation

import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.model.IGithubUsersRepo
import com.example.lesson1_mvp.screens.AndroidScreens
import com.example.lesson1_mvp.view.UserItemView
import com.example.lesson1_mvp.view.ui.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserPresenter(
    val router: Router,
    val currentUser: GithubUser,
    val repos: IGithubUsersRepo,
    val uiScheduler: Scheduler
) : MvpPresenter<UserView>() {

    class ReposListPresenter : IUserListPresenter {
        val repos = mutableListOf<GithubRepositoryRepo>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: UserItemView) {
            val repo = repos[view.pos]
            repo.let { view.loadRepos(it) }

        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        viewState.init()
        loadData()
        reposListPresenter.itemClickListener = { itemView ->
            var repo = reposListPresenter.repos[itemView.pos]
            router.navigateTo(AndroidScreens.repo(repo))
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun loadData() {
        repos.getRepos(currentUser.login)
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                reposListPresenter.repos.clear()
                reposListPresenter.repos.addAll(repos)
                viewState.updateRepos(repos)
            }, {
                println("Error: ${it.message}")
            })
        viewState.updateUser(currentUser)
    }

}