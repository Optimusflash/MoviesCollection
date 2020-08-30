package com.optimus.moviescollection.presentation.popular.viewholders

import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.moviescollection.databinding.RvMovieSellFooterStateBinding
import com.optimus.moviescollection.utils.State

/**
 * Created by Dmitriy Chebotar on 27.08.2020.
 */
class PopularMoviesFooterViewHolder(
    private val binding: RvMovieSellFooterStateBinding,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): PopularMoviesFooterViewHolder {
            val rvMovieSellBinding = RvMovieSellFooterStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return PopularMoviesFooterViewHolder(rvMovieSellBinding, retryCallback)
        }
    }




    fun bind(state: State) {
        binding.pbFooter.visibility = if (state == State.LOADING) VISIBLE else INVISIBLE
        binding.tvMessageError.visibility = if (state == State.ERROR) VISIBLE else INVISIBLE
        binding.button.visibility = if (state == State.ERROR) VISIBLE else INVISIBLE
        binding.button.setOnClickListener { retryCallback.invoke()  }
    }

}