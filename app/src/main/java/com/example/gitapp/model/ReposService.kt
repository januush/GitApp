package com.example.gitapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposService {

    @GET("/repositories")
    fun getRepos(@Query("since") id: Long): Single<List<Repository>>  //"since" (page), "per_page" unavailable for repos

    companion object {
        fun getService(): ReposService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(ReposService::class.java)
        }
    }

}