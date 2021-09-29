package com.example.lesson1_mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepositoryRepo(
    @Expose val id: String? = null,
    @Expose val node_id: String? = null,
    @Expose val name: String? = null,
    @Expose val full_name: String? = null,
    @Expose val forks: String? = null,
) : Parcelable