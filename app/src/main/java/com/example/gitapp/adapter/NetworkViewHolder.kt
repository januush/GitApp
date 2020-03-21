package com.example.gitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.util.NetworkState
import com.example.gitapp.util.Status
import kotlinx.android.synthetic.main.item_network_state.view.*

class NetworkViewHolder(val view: View, private val retryCallback: () -> Unit) : RecyclerView.ViewHolder(view) {


    fun bind(networkState: NetworkState?) {
        //error message
        itemView.errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
        if (networkState?.message != null) {
            itemView.errorMessageTextView.text = networkState.message
        }

       itemView.loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
            return NetworkViewHolder(view, retryCallback)
        }
    }

}