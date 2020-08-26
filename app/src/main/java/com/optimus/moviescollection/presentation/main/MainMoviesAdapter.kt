package com.optimus.moviescollection.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.moviescollection.data.model.Movie
import com.optimus.moviescollection.databinding.RvMovieSellBinding
import com.optimus.moviescollection.extensions.dateFormat
import com.optimus.moviescollection.extensions.loadImage

/**
 * Created by Dmitriy Chebotar on 25.08.2020.
 */
class MainMoviesAdapter: RecyclerView.Adapter<MainMoviesAdapter.PopularMoviesViewHolder>() {

    private val popularMovies = mutableListOf<Movie>()
    fun updateData(list: List<Movie>){
        popularMovies.clear()
        popularMovies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val inflate = RvMovieSellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMoviesViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(popularMovies[position])
    }

    override fun getItemCount() = popularMovies.size

    inner class PopularMoviesViewHolder(private val binding: RvMovieSellBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie){
            binding.ivPoster.loadImage(movie.posterPath)
            binding.tvMovieTitle.text = movie.title
            binding.tvRating.text = movie.rating.toString()
            binding.tvDate.text = movie.date.dateFormat()
        }


    }
}