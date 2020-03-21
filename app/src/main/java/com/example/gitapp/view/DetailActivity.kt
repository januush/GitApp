package com.example.gitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.gitapp.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val desciption: TextView = findViewById(R.id.detailDescription) as TextView

        val text = intent.getStringExtra("description")

        desciption.text = text
        }



}
