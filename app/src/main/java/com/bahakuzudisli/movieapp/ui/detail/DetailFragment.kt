package com.bahakuzudisli.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.bahakuzudisli.movieapp.MainActivity
import com.bahakuzudisli.movieapp.R
import com.bahakuzudisli.movieapp.databinding.FragmentDetailBinding
import com.bahakuzudisli.movieapp.util.loadImage


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val movieId = arguments?.let {
            DetailFragmentArgs.fromBundle(it).movieId
        }

        if (movieId != null) {
            viewModel.getMovieDetail(movieId)
        }
        observeEvents()

        return binding.root
    }

    private fun observeEvents() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            binding.textViewErrorDetail.text = error
            binding.textViewErrorDetail.isVisible = true
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBarDetail.isVisible = loading
        }

        viewModel.movieRespone.observe(viewLifecycleOwner) { movie ->
            (requireActivity() as MainActivity).supportActionBar?.title = movie.title
            binding.imageViewDetail.loadImage(movie.backdropPath)
            val voteAverage: Double? = movie?.voteAverage
            if (voteAverage != null) {
                val formattedVote = String.format("%.1f", voteAverage)
                binding.textViewDetailVote.text = formattedVote
            } else {
                // Handle null case, if needed
                binding.textViewDetailVote.text = "N/A"
            }
            binding.textViewDetailLanguage.text = movie.spokenLanguages?.first()?.englishName
            binding.textViewDetailStudio.text = movie.productionCompanies?.first()?.name
            binding.textViewDetailOverview.text = movie.overview.toString()
            binding.movieDetailGroup.isVisible = true

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}