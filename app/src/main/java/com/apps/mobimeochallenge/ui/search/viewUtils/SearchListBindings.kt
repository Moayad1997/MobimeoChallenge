package com.apps.mobimeochallenge.ui.search.viewUtils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apps.service.repository.gifRepository.response.Gif


/**
 * [BindingAdapter]s for the [Gif]s list.
 */
@BindingAdapter("app:items")
fun setItems(searchListView: RecyclerView, items: List<Gif>) {
    (searchListView.adapter as SearchAdapter).submitList(items)
}
