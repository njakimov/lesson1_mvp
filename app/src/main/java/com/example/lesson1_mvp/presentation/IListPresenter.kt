package com.example.lesson1_mvp.presentation

import com.example.lesson1_mvp.view.IItemView
import com.example.lesson1_mvp.view.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>