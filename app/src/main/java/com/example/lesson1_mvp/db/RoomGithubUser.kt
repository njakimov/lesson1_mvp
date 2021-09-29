package com.example.lesson1_mvp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class RoomGithubUser(
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String,
    var repos_url: String
)
