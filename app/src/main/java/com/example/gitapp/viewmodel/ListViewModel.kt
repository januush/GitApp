package com.example.gitapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitapp.model.Repository

class ListViewModel: ViewModel() {
    val repos = MutableLiveData<List<Repository>>()
    val reposLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchRepos()
    }

    private fun fetchRepos() {
        //I hide the implementation of the function by calling it from the refresh()
        //For now just provide some mock data to build the View. The backend part is to do.

        val mockData: List<Repository> = listOf(
            Repository("Repo1","www.repo1.com"),
            Repository("Repo2","www.repo1.com"),
            Repository("Repo3","www.repo1.com"),
            Repository("Repo4","www.repo1.com"),
            Repository("Repo5","www.repo1.com"),
            Repository("Repo6","www.repo1.com"),
            Repository("Repo7","www.repo1.com"),
            Repository("Repo8","www.repo1.com"),
            Repository("Repo9","www.repo1.com"),
            Repository("Repo10","www.repo1.com")
        )
        reposLoadError.value = false //No error in loading data
        loading.value = false //it is not loading
        repos.value = mockData //update for listeners
    }

}