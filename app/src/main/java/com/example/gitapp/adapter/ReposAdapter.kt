package com.example.gitapp.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.model.Repository
import com.example.gitapp.util.NetworkState
import java.lang.IllegalArgumentException

class ReposAdapter(private val retryCallback: () -> Unit) : PagedListAdapter<Repository, RecyclerView.ViewHolder>(RepoDiffCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.repo_row -> RepoViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)){
            R.layout.repo_row -> (holder as RepoViewHolder).bind(getItem(position))
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if(hasExtraRow() && position == itemCount - 1){
            R.layout.item_network_state
        } else{
            R.layout.repo_row
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }


    companion object{
        val RepoDiffCallback = object : DiffUtil.ItemCallback<Repository>(){
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                TODO("Not yet implemented")
            }
        }
    }
}