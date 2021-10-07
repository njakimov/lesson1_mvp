package com.example.lesson1_mvp.di

import com.example.lesson1_mvp.presentation.RepoPresenter
import com.example.lesson1_mvp.presentation.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepoPresenter)
}