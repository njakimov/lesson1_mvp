package com.example.lesson1_mvp.cache

import com.example.lesson1_mvp.db.Database
import com.example.lesson1_mvp.db.RoomGithubRepository
import com.example.lesson1_mvp.model.GithubRepositoryRepo
import com.example.lesson1_mvp.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubReposCache(val db: Database) : IRoomGithubReposCache {
    override fun setCache(user: GithubUser, repositories: List<GithubRepositoryRepo>): Completable =
        Completable.fromAction {
            Single.fromCallable {
                val roomUser = user.login?.let {
                    db.userDao.findByLogin(it)
                } ?: throw RuntimeException("No such user in cache")
                val roomRepos = repositories.map {
                    RoomGithubRepository(
                        it.id ?: "",
                        it.name ?: "",
                        it.node_id ?: "",
                        it.full_name ?: "",
                        it.forks ?: "",
                        roomUser.id
                    )
                }
                db.repositoryDao.insert(roomRepos)
                repositories
            }
        }.subscribeOn(Schedulers.io())


    override fun getCache(user: GithubUser): Single<List<GithubRepositoryRepo>> =
        Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw RuntimeException("No such user in cache")
            db.repositoryDao.findForUser(roomUser.id).map {
                GithubRepositoryRepo(it.id, it.name, it.node_id, it.full_name, it.forks)
            }
        }.subscribeOn(Schedulers.io())
}