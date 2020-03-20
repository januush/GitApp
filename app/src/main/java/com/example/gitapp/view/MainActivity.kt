package com.example.gitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapp.R
import com.example.gitapp.adapter.ReposAdapter
import com.example.gitapp.model.Repository
import com.example.gitapp.util.NetworkState
import com.example.gitapp.util.Status
import com.example.gitapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var reposViewModel: ListViewModel
    private lateinit var reposAdapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reposViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        initAdapter()
        initSwipeToRefresh()
    }

    private fun initSwipeToRefresh() {
        reposViewModel.getRefreshState().observe(this,Observer { networkState ->
            if(reposAdapter.currentList != null){
                if(reposAdapter.currentList!!.size > 0){
                    swipeRefreshLayout.isRefreshing = networkState?.status == NetworkState.LOADING.status
                } else{
                    setInitialLoadingState(networkState)
                }
            } else{
                setInitialLoadingState(networkState)
            }
        })
        swipeRefreshLayout.setOnRefreshListener { reposViewModel.refresh() }
    }

    private fun initAdapter(){
        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        reposAdapter = ReposAdapter {
            reposViewModel.retry()
        }
        reposList.layoutManager = linearLayoutManager
        reposList.adapter = reposAdapter
        reposViewModel.reposList.observe(this, Observer<PagedList<Repository>> {reposAdapter.submitList(it)})
        reposViewModel.getNetworkState().observe(this, Observer<NetworkState> {reposAdapter.setNetworkState(it)})
    }

    private fun setInitialLoadingState(networkState: NetworkState?) {
        //error message
        list_error.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
        if (networkState?.message != null) {
            list_error.text = networkState.message
        }

        //loading and retry
        //retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        loading_progress_bar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE

        swipeRefreshLayout.isEnabled = networkState?.status == Status.SUCCESS
        //retryLoadingButton.setOnClickListener { reposViewModel.retry() }
    }

}
