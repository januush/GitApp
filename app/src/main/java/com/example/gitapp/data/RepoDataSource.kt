package com.example.gitapp.data

import androidx.paging.ItemKeyedDataSource
import com.example.gitapp.model.ReposService
import com.example.gitapp.model.Repository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepoDataSource(
    private val reposService: ReposService,
    private val compositeDisposable: CompositeDisposable)
    : ItemKeyedDataSource<Long, Repository>() {

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

       compositeDisposable.add(reposService.getRepos(1).subscribe({repos ->
            callback.onResult(repos)
        }, {
           //...
        })
       )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Repository>) {
        //get the repos from the api after id

        compositeDisposable.add(reposService.getRepos(params.key).subscribe({
            repos ->
            callback.onResult(repos)
        }, {
            //...
        }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Repository>) {
        //not implemented
    }

    override fun getKey(item: Repository): Long {
        return item.id
    }
 }
