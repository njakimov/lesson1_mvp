package com.example.lesson1_mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.databinding.FragmentUsersBinding
import com.example.lesson1_mvp.db.Database
import com.example.lesson1_mvp.di.UserSubcomponent
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.model.GlideImageLoader
import com.example.lesson1_mvp.presentation.UsersPresenter
import com.example.lesson1_mvp.view.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    @Inject
    lateinit var database: Database

    private var vb: FragmentUsersBinding? = null

    var userSubcomponent: UserSubcomponent? = null

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(AndroidSchedulers.mainThread()).apply {
            userSubcomponent = App.instance.initUserSubcomponent()
            userSubcomponent?.inject(this)
        }
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    fun addUserList(user: GithubUser) {
        updateList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}