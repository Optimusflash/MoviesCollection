package com.optimus.moviescollection.presentation.details.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.moviescollection.data.model.credits.Cast
import com.optimus.moviescollection.presentation.details.viewholders.CreditViewHolder

/**
 * Created by Dmitriy Chebotar on 01.09.2020.
 */
class CreditAdapter: RecyclerView.Adapter<CreditViewHolder>() {

    private val items = mutableListOf<Cast>()

    fun updateData(list: List<Cast>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CreditViewHolder.create(parent)

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        holder.bind(items[position])
    }
    override fun getItemCount() = items.size
}