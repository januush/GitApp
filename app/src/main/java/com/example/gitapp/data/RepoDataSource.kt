package com.example.gitapp.data

import androidx.paging.ItemKeyedDataSource
import com.example.gitapp.model.ReposService
import com.example.gitapp.model.Repository
import io.reactivex.disposables.CompositeDisposable

class RepoDataSource(
    private val reposService: ReposService,
    private val compositeDisposable: CompositeDisposable)
    : ItemKeyedDataSource<Long, Repository>() {
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Repository>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Repository>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Repository>) {
        TODO("Not yet implemented")
    }

    override fun getKey(item: Repository): Long {
        TODO("Not yet implemented")
    }
}