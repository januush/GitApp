package com.example.gitapp.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.example.gitapp.model.ReposService
import com.example.gitapp.model.Repository
import com.example.gitapp.util.NetworkState
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class RepoDataSource(
    private val reposService: ReposService,
    private val compositeDisposable: CompositeDisposable)
    : ItemKeyedDataSource<Long, Repository>() {


    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Repository>) {

        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(reposService.getRepos(1).subscribe({
            repos ->

            callback.onResult(repos)
        }, { throwabe ->

            networkState.postValue(NetworkState.error(throwabe.message))
            initialLoad.postValue(NetworkState.error(throwabe.message))
        }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Repository>) {

        networkState.postValue(NetworkState.LOADING)

        //get the repos from the api after id
        compositeDisposable.add(reposService.getRepos(params.key).subscribe({
            repos ->
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(repos)
        }, { throwabe ->
            networkState.postValue(NetworkState.error(throwabe.message))
        }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Repository>) {

    }

    override fun getKey(item: Repository): Long {
        return item.id
    }


    }
