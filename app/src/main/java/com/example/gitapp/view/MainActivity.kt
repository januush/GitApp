package com.example.gitapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapp.R
import com.example.gitapp.adapter.ReposAdapter
import com.example.gitapp.model.Repository
import com.example.gitapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var reposViewModel: ListViewModel
    private lateinit var reposAdapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reposViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        reposAdapter = ReposAdapter {
            reposViewModel.retry()
        }

        reposList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reposAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            reposViewModel.refresh()
        }

        //Connect the View with the ListViewModel
        observeViewModel()

    }

    fun observeViewModel(){
        reposViewModel.reposList.observe(this, Observer<PagedList<Repository>> {reposAdapter.submitList(it)
            loading_progress_bar.visibility = View.GONE
        })

    }

}
