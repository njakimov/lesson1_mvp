package com.example.lesson1_mvp.cache

import com.example.lesson1_mvp.db.Database
import com.example.lesson1_mvp.db.RoomGithubUser
import com.example.lesson1_mvp.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(val db: Database) : IRoomGithubUsersCache {
    override fun setCache(users: List<GithubUser>): Completable = Completable.fromAction {
        Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id ?: "", user.login ?: "", user.avatarUrl ?: "", user.repos_url ?: ""
                )
            }
            db.userDao.insert(roomUsers)
            users
        }
    }.subscribeOn(Schedulers.io())


    override fun getCache(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(
                roomUser.id, roomUser.login, roomUser.avatarUrl,
                roomUser.repos_url
            )
        }
    }.subscribeOn(Schedulers.io())
}