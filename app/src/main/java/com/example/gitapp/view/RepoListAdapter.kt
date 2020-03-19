package com.example.gitapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.model.Repository
import kotlinx.android.synthetic.main.repo_row.view.*

class RepoListAdapter(var repos: ArrayList<Repository>): RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    fun updateRepos(newRepos: List<Repository>){
        repos.clear()
        repos.addAll(newRepos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.repo_row, parent, false)
    )


    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    class RepoViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val repoName = view.repoName
        private val repoLink = view.githublink


        fun bind (repo: Repository){
            repoName.text = repo.repoName
            repoLink.text = repo.repoLink

           //TODO loading the image with Picasso
        }
    }
}