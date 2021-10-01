package com.example.lesson1_mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.databinding.FragmentRepoBinding
import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.presentation.RepoPresenter
import com.example.lesson1_mvp.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class RepoFragment(repo: GithubRepositoryRepo) : MvpAppCompatFragment(), UserView,
    BackButtonListener {
    companion object {
        private const val REPO = "repo"
        fun newInstance(repo: GithubRepositoryRepo) = RepoFragment(repo).apply {
            arguments = Bundle().apply {
                putParcelable(REPO, repo)
            }
        }
    }

    //    private var repo: GithubRepositoryRepo = GithubRepositoryRepo()
    private var vb: FragmentRepoBinding? = null

    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(repo).apply {
            App.instance.appComponent.inject(this)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRepoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }


    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun init() {

    }

    override fun updateUser(user: GithubUser) {
//        vb?.tvUserLogin?.text = user.repos_url

    }

    override fun updateRepos(repos: List<GithubRepositoryRepo>) {
    }

    override fun updateRepo(repo: GithubRepositoryRepo) {
        vb?.tvFork?.text = repo.forks
    }


}