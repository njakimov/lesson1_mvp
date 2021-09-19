package com.example.lesson1_mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson1_mvp.App
import com.example.lesson1_mvp.databinding.FragmentUsersBinding
import com.example.lesson1_mvp.model.GithubUser
import com.example.lesson1_mvp.model.GithubUsersRepo
import com.example.lesson1_mvp.presentation.UsersPresenter
import com.example.lesson1_mvp.service.MyObserver
import com.example.lesson1_mvp.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private var vb: FragmentUsersBinding? = null
    private val store: GithubUsersRepo = GithubUsersRepo()

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            store,
            App.instance.router
        )
    }


    //private val adapter by lazy { UsersRVAdapter(presenter.usersListPresenter) }
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
            val myObserver = MyObserver<GithubUser>()
            myObserver.producer.sendMessage(GithubUser("user50"))
            MyObserver.Consumer(myObserver.producer, { user -> addUserList(user) }).exec()

        }.root
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    fun addUserList(user: GithubUser) {
        store.addUser(user)
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