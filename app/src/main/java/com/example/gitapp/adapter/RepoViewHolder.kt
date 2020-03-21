package com.example.gitapp.adapter

import android.content.Intent
import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.model.Repository
import com.example.gitapp.util.getDrawable
import com.example.gitapp.view.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repo_row.view.*

class RepoViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val progressDrawable = getDrawable(view.context)

    fun bind(repo: Repository?){
        itemView.repoName.text = repo?.repoName
        itemView.githublink.text = repo?.repoLink

        Linkify.addLinks(itemView.githublink,Linkify.WEB_URLS)

        Picasso.get()
            .load(repo?.repoImage?.avatar_url)
            .placeholder(progressDrawable)
            .into(itemView.repoImage)


        itemView.repoDetails.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(v?.context, itemView.repoName.getText().toString() + " clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(v?.context,DetailActivity::class.java)

                intent.putExtra("html_url",repo?.repoLink)
                intent.putExtra("full_name",repo?.repoFullName)
                intent.putExtra("description",repo?.repoDescription)
                intent.putExtra("isPrivate",repo?.repoIsPrivate)

                v?.context?.startActivity(intent)
            }
        })
    }

    companion object{
        fun create(parent: ViewGroup): RepoViewHolder {
            val layoutInflater= LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.repo_row,parent,false)
            return RepoViewHolder(view)
        }
    }


}