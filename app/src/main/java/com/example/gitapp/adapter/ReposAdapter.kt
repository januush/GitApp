package com.example.gitapp.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.model.Repository

class ReposAdapter(private val retryCallback: () -> Unit) : PagedListAdapter<Repository, RecyclerView.ViewHolder>(RepoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
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