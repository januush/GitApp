package com.example.gitapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ReposService {

    private val BASE_URL = "https://api.github.com"
    private val api:ReposAPI

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ReposAPI::class.java)
    }

    //The only function for getting the information from backend
    fun getRepos(): Single<List<Repository>> {
        return api.getRepos()
    }
}