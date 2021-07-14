package com.apps.mobimeochallenge.ui.search.viewUtils

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apps.mobimeochallenge.ui.search.SearchViewModel
import com.apps.service.repository.gifRepository.response.Gif


/**
 * Adapter for the task list. Has a reference to the [SearchViewModel] to send actions back to it.
 */
class SearchAdapter(private val searchViewModel: SearchViewModel) :
    ListAdapter<Gif, SearchViewHolder>(SearchDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindView(item)
    }


}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class SearchDiffCallback : DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem == newItem
    }
}