package com.optimus.moviescollection.presentation.popular.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.moviescollection.data.model.movie.Movie
import com.optimus.moviescollection.databinding.RvMovieSellBinding

import com.optimus.moviescollection.extensions.dateFormat
import com.optimus.moviescollection.extensions.loadRoundedCornersImage

/**
 * Created by Dmitriy Chebotar on 27.08.2020.
 */
class PopularMoviesViewHolder (private val binding: RvMovieSellBinding, private val onMovieClick: (id: Int) -> Unit) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun create(parent: ViewGroup, onMovieClick: (id: Int) -> Unit): PopularMoviesViewHolder {
            val rvMovieSellBinding = RvMovieSellBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return PopularMoviesViewHolder(rvMovieSellBinding, onMovieClick)
        }
    }

    fun bind(movie: Movie){
        binding.ivPoster.loadRoundedCornersImage(movie.posterPath)
        binding.ivPoster.setOnClickListener { onMovieClick.invoke((movie.id).toInt()) }
        binding.tvMovieTitle.text = movie.title
        binding.tvRating.text = movie.rating.toString()
        binding.tvDate.text = movie.date?.dateFormat()
    }

}