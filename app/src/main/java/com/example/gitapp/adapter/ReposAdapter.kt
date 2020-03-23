package com.example.gitapp.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.model.Repository
import com.example.gitapp.util.NetworkState
import java.lang.IllegalArgumentException

class ReposAdapter : PagedListAdapter<Repository, RecyclerView.ViewHolder>(RepoDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            return RepoViewHolder.create(parent)



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val repoItem = getItem(position)
            if(repoItem!=null){
                (holder as RepoViewHolder).bind(repoItem)
            }

    }

    companion object{
        val RepoDiffCallback = object : DiffUtil.ItemCallback<Repository>(){
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }
        }
    }
}