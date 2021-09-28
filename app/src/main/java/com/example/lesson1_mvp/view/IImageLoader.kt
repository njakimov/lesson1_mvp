package com.example.lesson1_mvp.view

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}