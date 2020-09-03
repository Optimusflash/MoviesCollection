package com.optimus.moviescollection.presentation.details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.moviescollection.R
import com.optimus.moviescollection.data.model.movie.MovieDetails
import com.optimus.moviescollection.databinding.FragmentDetailsBinding
import com.optimus.moviescollection.di.Injector
import com.optimus.moviescollection.di.ViewModelFactory
import com.optimus.moviescollection.extensions.*
import com.optimus.moviescollection.presentation.details.adapters.CreditAdapter
import com.optimus.moviescollection.presentation.details.viewmodel.DetailsViewModel
import javax.inject.Inject


class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding : FragmentDetailsBinding
    private val creditAdapter by lazy { CreditAdapter() }

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
        binding.rvCredits.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCredits.adapter = creditAdapter
        binding.btnAdd.setOnClickListener { showToast() }
    }

    private fun setObservers() {
        detailsViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            updateUi(it)
        })

        detailsViewModel.credits.observe(viewLifecycleOwner, Observer {
            creditAdapter.updateData(it.cast)
        })
    }

    private fun updateUi(movieDetails: MovieDetails) {
        val rating = "${movieDetails.rating}/10"
        binding.ivMovie.loadRoundCornerImage(movieDetails.imageUrl)
        binding.tvMovieTitle.text = movieDetails.title
        binding.tvMovieDescription.text = movieDetails.overview
        binding.tvRating.text = rating
        binding.tvPopularity.text = movieDetails.popularity.toString()
        binding.tvMovieDate.text = movieDetails.date.dateFormat(getString(R.string.date_format_pattern))
        binding.tvMovieDuration.text = movieDetails.duration.formatDuration()
        binding.tvVoteCount.text = movieDetails.voteCount.toString()
        binding.chipGroup2.addChips(
            requireContext(),
            movieDetails.genres,
            checkable = false,
            clickListener = null
        )
    }

}