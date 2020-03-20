package com.example.gitapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gitapp.data.RepoDataSource
import com.example.gitapp.data.RepoDataSourceFactory
import com.example.gitapp.model.ReposService
import com.example.gitapp.model.Repository
import com.example.gitapp.util.NetworkState
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

    fun retry(){
        sourceFactory.reposDataSourceLiveData.value!!.retry()
    }

    fun refresh(){
        sourceFactory.reposDataSourceLiveData.value!!.invalidate()
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<RepoDataSource,NetworkState>(
        sourceFactory.reposDataSourceLiveData, {it.networkState}
    )

    fun getRefreshState(): LiveData<NetworkState> = Transformations.switchMap<RepoDataSource,NetworkState>(
        sourceFactory.reposDataSourceLiveData, {it.initialLoad}
    )

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}