package com.example.lesson1_mvp.di

import com.example.lesson1_mvp.MainActivity
import com.example.lesson1_mvp.MainPresenter
import com.example.lesson1_mvp.presentation.RepoPresenter
import com.example.lesson1_mvp.presentation.UserPresenter
import com.example.lesson1_mvp.presentation.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)

    fun inject(userPresenter: UserPresenter)

    fun inject(repoPresenter: RepoPresenter)


}