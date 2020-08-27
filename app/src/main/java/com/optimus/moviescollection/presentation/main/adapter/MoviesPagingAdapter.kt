package com.optimus.moviescollection.presentation.main.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.optimus.moviescollection.data.model.Movie
import com.optimus.moviescollection.presentation.main.viewholders.PopularMoviesFooterViewHolder
import com.optimus.moviescollection.presentation.main.viewholders.PopularMoviesViewHolder
import com.optimus.moviescollection.utils.State

/**
 * Created by Dmitriy Chebotar on 26.08.2020.
 */
class MoviesPagingAdapter (val onRetryClick: ()->Unit) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(
    MOVIE_COMPARATOR
) {
    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = State.LOADING

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DATA_VIEW_TYPE -> {
                PopularMoviesViewHolder.create(parent)
            }
            else -> {
                PopularMoviesFooterViewHolder.create(parent, onRetryClick)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            DATA_VIEW_TYPE -> {
                getItem(position)?.let {
                    (holder as PopularMoviesViewHolder).bind(it)
                }
            }
            FOOTER_VIEW_TYPE -> {
                (holder as PopularMoviesFooterViewHolder).bind(state)
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}