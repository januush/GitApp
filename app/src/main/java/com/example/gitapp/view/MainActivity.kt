package com.example.gitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapp.R
import com.example.gitapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val reposAdapter = RepoListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        reposList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reposAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        //Connect the View with the ListViewModel
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.repos.observe(this, Observer {
            repositories -> repositories?.let {
            reposList.visibility = View.VISIBLE
            reposAdapter.updateRepos(it) }
        })

        viewModel.reposLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_progress_bar.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    list_error.visibility = View.GONE
                    reposList.visibility = View.GONE
                }
            }
        })
    }
}
