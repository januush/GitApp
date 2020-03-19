package com.example.gitapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitapp.model.ReposService
import com.example.gitapp.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private val reposService = ReposService()
    private val disposable = CompositeDisposable()

    val repos = MutableLiveData<List<Repository>>()
    val reposLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchRepos()
    }

    private fun fetchRepos() {
        loading.value = true
        disposable.add(
            reposService.getRepos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Repository>>(){
                    override fun onSuccess(value: List<Repository>?) {
                        repos.value = value
                        reposLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        reposLoadError.value = true
                        loading.value = false
                    }

            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear() //clears the connection with RxJava
    }

}