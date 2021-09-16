package com.example.lesson1_mvp.screens

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(userIdx: Int): Screen

}