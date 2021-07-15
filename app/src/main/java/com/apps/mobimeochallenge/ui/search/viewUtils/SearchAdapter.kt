package com.apps.mobimeochallenge.ui.search.viewUtils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.service.repository.gifRepository.response.Gif


class SearchAdapter(
    private var dataList: MutableList<Gif>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = dataList[position]
        holder.bindView(item, onClickListener)
    }

    override fun getItemCount(): Int = dataList.size

    fun updateData(newList: List<Gif>) {
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

    fun setNewList(newList: List<Gif>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }


}

