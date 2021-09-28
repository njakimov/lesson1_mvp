package com.example.lesson1_mvp.screens

import com.example.lesson1_mvp.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen

}