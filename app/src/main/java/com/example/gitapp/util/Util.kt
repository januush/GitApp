package com.example.gitapp.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun getDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth=10f
        centerRadius = 50f
        start()
    }
}