package com.example.lesson1_mvp.db

import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

}