package com.example.lesson1_mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.databinding.FragmentUserBinding
import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.presentation.UserPresenter
import com.example.lesson1_mvp.view.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment(var user: GithubUser) : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val USER = "user"
        fun newInstance(user: GithubUser) = UserFragment(user).apply {
            arguments = Bundle().apply {
                putParcelable(USER, user)
            }
        }
    }

    private var vb: FragmentUserBinding? = null

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            user,
            AndroidSchedulers.mainThread()
        ).apply {
            App.instance.initRepositorySubcomponent()?.inject(this)
        }
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