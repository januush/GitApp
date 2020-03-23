package com.example.gitapp.view

import android.os.Bundle
import android.text.util.Linkify
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gitapp.R
import com.squareup.picasso.Picasso
//TODO Butterknife for the Views

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val progressDrawable = com.example.gitapp.util.getDrawable(this)

        val descTv: TextView = findViewById(R.id.description) as TextView
        val nameTv: TextView = findViewById(R.id.repoName) as TextView
        val linkTv: TextView = findViewById(R.id.githublink) as TextView
        val avatar: ImageView = findViewById(R.id.repoImage) as ImageView
        val isprivateTv: TextView = findViewById(R.id.isPrivate) as TextView
        val ownerTv: TextView = findViewById(R.id.ownerName) as TextView

        val image = intent.getStringExtra("image")

        linkTv.text = intent.getStringExtra("html_url")
        descTv.text = intent.getStringExtra("description")
        nameTv.text = intent.getStringExtra("full_name")
        ownerTv.text = intent.getStringExtra("owner_name")
        Linkify.addLinks(linkTv,Linkify.WEB_URLS)

        if(intent.getBooleanExtra("type",false)){
            isprivateTv.text = "Private"
        } else {
            isprivateTv.text = "Public"
        }

        Picasso.get()
            .load(image)
            .placeholder(progressDrawable)
            .into(avatar)
        }

}
