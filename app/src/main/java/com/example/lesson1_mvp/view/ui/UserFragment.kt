package com.example.lesson1_mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.cache.RoomGithubReposCache
import com.example.lesson1_mvp.databinding.FragmentUserBinding
import com.example.lesson1_mvp.db.Database
import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.network.AndroidNetworkStatus
import com.example.lesson1_mvp.presentation.UserPresenter
import com.example.lesson1_mvp.view.BackButtonListener
import com.example.lesson1_mvp.web.ApiHolder
import com.example.lesson1_mvp.web.RetrofitGithubRepositoryRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        fun newInstance(_user: GithubUser): UserFragment {
            val fragment = UserFragment()
            fragment.user = _user;
            return fragment;
        }
    }

    private var user: GithubUser = GithubUser()
    private var vb: FragmentUserBinding? = null

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            App.instance.router,
            user,
            RetrofitGithubRepositoryRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                RoomGithubReposCache(Database.getInstance())
            ),
            AndroidSchedulers.mainThread(),
        )
    }

    private var adapter: ReposRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }


    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun init() {
        vb?.rvUserRepositories?.layoutManager = LinearLayoutManager(requireContext())
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        vb?.rvUserRepositories?.adapter = adapter
    }

    override fun updateUser(user: GithubUser) {
//        vb?.tvUserLogin?.text = user.repos_url

    }

    override fun updateRepos(repos: List<GithubRepositoryRepo>) {
        adapter?.notifyDataSetChanged()
    }

    override fun updateRepo(repo: GithubRepositoryRepo) {
        TODO("Not yet implemented")
    }


}