package com.example.gitapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gitapp.data.RepoDataSourceFactory
import com.example.gitapp.model.ReposService
import com.example.gitapp.model.Repository
import io.reactivex.disposables.CompositeDisposable

class ListViewModel : ViewModel() {

    var reposList: LiveData<PagedList<Repository>>
    private val compositeDisposable = CompositeDisposable()
    private val sourceFactory: RepoDataSourceFactory
    private val pageSize = 15

    init {
        sourceFactory = RepoDataSourceFactory(compositeDisposable, ReposService.getService())
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize).setInitialLoadSizeHint(pageSize*2)
            .setEnablePlaceholders(false)
            .build()
        reposList = LivePagedListBuilder<Long,Repository>(sourceFactory,config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}