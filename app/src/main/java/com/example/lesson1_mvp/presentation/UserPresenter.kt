package com.example.lesson1_mvp.presentation

import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.model.IGithubRepositoryRepo
import com.example.lesson1_mvp.screens.AndroidScreens
import com.example.lesson1_mvp.view.UserItemView
import com.example.lesson1_mvp.view.ui.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter(
    val currentUser: GithubUser,
    val uiScheduler: Scheduler
) : MvpPresenter<UserView>() {


    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoryScopeContainer: IRepositoryScopeContainer

    @Inject
    lateinit var repos: IGithubRepositoryRepo

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
        repos.getRepos(currentUser)
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

    override fun onDestroy() {
        repositoryScopeContainer.releaseRepositoryScope()
        super.onDestroy()
    }

}