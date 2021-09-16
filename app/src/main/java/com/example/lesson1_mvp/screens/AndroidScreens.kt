package com.example.lesson1_mvp.screens

import com.example.lesson1_mvp.view.ui.UserFragment
import com.example.lesson1_mvp.view.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(userIdx: Int) = FragmentScreen { UserFragment.newInstance(userIdx) }
}