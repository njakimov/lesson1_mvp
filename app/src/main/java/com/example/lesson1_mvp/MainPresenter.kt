package com.example.lesson1_mvp

import com.example.lesson1_mvp.screens.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backPressed() {
        router.exit()
    }
}