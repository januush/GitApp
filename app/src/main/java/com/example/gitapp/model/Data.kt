package com.example.gitapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body

data class Repository(
    @SerializedName("full_name")
    val repoName: String?,
    @SerializedName("html_url")
    val repoLink: String?,

    @SerializedName("owner") var repoImage: RepoOwner)

//TODO how about reference to that class?
class RepoOwner (
    var avatar_url: String?
)
