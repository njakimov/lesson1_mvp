package com.example.lesson1_mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.databinding.FragmentUserBinding
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.model.GithubUsersRepo
import com.example.lesson1_mvp.presentation.UserPresenter
import com.example.lesson1_mvp.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        fun newInstance(_userIdx: Int): UserFragment {
            val fragment = UserFragment()
            fragment.userIdx = _userIdx;
            return fragment;
        }
    }

    private var userIdx: Int = -1
    private var vb: FragmentUserBinding? = null

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            GithubUsersRepo(),
            App.instance.router,
            userIdx
        )
    }


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
        vb?.userIdx?.text = userIdx.toString();
    }

    override fun updateUser(user: GithubUser) {
        vb?.userIdx?.text = user.login
    }


}