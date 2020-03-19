package com.example.gitapp.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("full_name")
    val repoName: String?,
    @SerializedName("html_url")
    val repoLink: String?

    )
