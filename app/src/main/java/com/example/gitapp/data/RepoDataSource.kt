package com.example.gitapp.data

import androidx.paging.ItemKeyedDataSource
import com.example.gitapp.model.Repository

class RepoDataSource(

): ItemKeyedDataSource<Long, Repository>() {
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