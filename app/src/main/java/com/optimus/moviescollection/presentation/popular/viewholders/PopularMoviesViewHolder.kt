package com.optimus.moviescollection.presentation.popular.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.moviescollection.data.model.Movie
import com.optimus.moviescollection.databinding.RvMovieSellBinding
import com.optimus.moviescollection.extensions.dateFormat
import com.optimus.moviescollection.extensions.loadImage

/**
 * Created by Dmitriy Chebotar on 27.08.2020.
 */
class PopularMoviesViewHolder (private val binding: RvMovieSellBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun create(parent: ViewGroup): PopularMoviesViewHolder {
            val rvMovieSellBinding = RvMovieSellBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return PopularMoviesViewHolder(rvMovieSellBinding)
        }
    }

    fun bind(movie: Movie){
        binding.ivPoster.loadImage(movie.posterPath)
        binding.tvMovieTitle.text = movie.title
        binding.tvRating.text = movie.rating.toString()
        binding.tvDate.text = movie.date?.dateFormat()
    }

}