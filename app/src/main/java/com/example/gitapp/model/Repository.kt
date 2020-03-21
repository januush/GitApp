package com.example.gitapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body

data class Repository(
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("name")
    val repoName: String?,
    @SerializedName("html_url")
    val repoLink: String?,
    @SerializedName("private")
    val repoIsPrivate: Boolean?,
    @SerializedName("description")
    val repoDescription: String?,
    @SerializedName("full_name")
    val repoFullName: String?,

    @SerializedName("owner") var repoImage: RepoOwner)

//TODO how about reference to that class?
class RepoOwner (
    var avatar_url: String?
)
