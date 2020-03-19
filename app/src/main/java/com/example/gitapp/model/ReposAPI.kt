package com.example.gitapp.model

import io.reactivex.Single
import retrofit2.http.GET

interface ReposAPI {
    @GET("/repositories")
    fun getRepos(): Single<List<Repository>>
}