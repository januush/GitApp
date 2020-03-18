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
            Repository("Repo1"),
            Repository("Repo2"),
            Repository("Repo3"),
            Repository("Repo4"),
            Repository("Repo5"),
            Repository("Repo6"),
            Repository("Repo7"),
            Repository("Repo8"),
            Repository("Repo9"),
            Repository("Repo10")
        )
        reposLoadError.value = false //No error in loading data
        loading.value = false //it is not loading
        repos.value = mockData //update for listeners
    }

}