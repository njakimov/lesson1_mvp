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


class RepoFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        fun newInstance(_repo: GithubRepositoryRepo): RepoFragment {
            val fragment = RepoFragment()
            fragment.repo = _repo;
            return fragment;
        }
    }

    private var repo: GithubRepositoryRepo = GithubRepositoryRepo()
    private var vb: FragmentRepoBinding? = null

    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(App.instance.router, repo)
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