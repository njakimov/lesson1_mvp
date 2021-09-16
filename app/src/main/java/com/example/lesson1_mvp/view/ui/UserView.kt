package com.example.lesson1_mvp.view.ui

import com.example.lesson1_mvp.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun init()
    fun updateUser(user: GithubUser)
}