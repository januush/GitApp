package com.example.gitapp.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.gitapp.model.ReposService
import com.example.gitapp.model.Repository
import io.reactivex.disposables.CompositeDisposable

class RepoDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val reposService: ReposService)
    : DataSource.Factory<Long, Repository>() {

    val reposDataSourceLiveData = MutableLiveData<RepoDataSource>()

    override fun create(): DataSource<Long, Repository> {
        val reposDataSource = RepoDataSource(reposService,compositeDisposable)
        reposDataSourceLiveData.postValue(reposDataSource)
        return reposDataSource
    }
}