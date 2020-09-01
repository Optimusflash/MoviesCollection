package com.optimus.moviescollection.presentation.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.optimus.moviescollection.data.model.MovieDetails
import com.optimus.moviescollection.databinding.FragmentDetailsBinding
import com.optimus.moviescollection.di.Injector
import com.optimus.moviescollection.di.ViewModelFactory
import com.optimus.moviescollection.extensions.addChips
import com.optimus.moviescollection.extensions.dateFormat
import com.optimus.moviescollection.extensions.loadRoundCornerImage
import com.optimus.moviescollection.extensions.setLightStatusBar
import javax.inject.Inject


class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding : FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setLightStatusBar(false)
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDaggerComponent()
        initViewModel()
        initViews()
        setObservers()

    }


    private fun initDaggerComponent() {
        Injector.getAppComponent().inject(this)
    }

    private fun initViewModel() {
        detailsViewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    private fun initViews() {
        arguments?.let {
            val movieId = DetailsFragmentArgs.fromBundle(it).movieId
            detailsViewModel.setMovieId(movieId)
        }
    }

    private fun setObservers() {
        detailsViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            updateUi(it)
        })
    }

    private fun updateUi(movieDetails: MovieDetails) {
        binding.ivMovie.loadRoundCornerImage(movieDetails.imageUrl)
        binding.tvMovieTitle.text = movieDetails.title
        binding.tvMovieDescription.text = movieDetails.overview
        binding.tvRating.text = movieDetails.rating.toString()
        binding.tvPopularity.text = movieDetails.popularity.toString()
        binding.tvMovieDate.text = movieDetails.date.dateFormat("dd.MM.yyyy")  //TODO
        binding.tvMovieDuration.text = movieDetails.duration.toString()
        binding.tvVoteCount.text = movieDetails.voteCount.toString()
        binding.chipGroup2.addChips(
            requireContext(),
            movieDetails.genres,
            checkable = false,
            clickListener = null
        )
    }

}