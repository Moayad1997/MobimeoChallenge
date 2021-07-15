package com.apps.mobimeochallenge.ui.search.viewUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.mobimeochallenge.databinding.SearchViewHolderBinding
import com.apps.service.repository.gifRepository.response.Gif

class SearchViewHolder(private val searchViewHolderBinding: SearchViewHolderBinding) :
    RecyclerView.ViewHolder(searchViewHolderBinding.root) {

    companion object {
        fun from(parent: ViewGroup): SearchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SearchViewHolderBinding.inflate(layoutInflater, parent, false)
            return SearchViewHolder(binding)
        }
    }

    fun bindView(gif: Gif, onClickListener: OnClickListener) {
        searchViewHolderBinding.gif = gif
        searchViewHolderBinding.clickListener = onClickListener
        searchViewHolderBinding.executePendingBindings()
    }


}