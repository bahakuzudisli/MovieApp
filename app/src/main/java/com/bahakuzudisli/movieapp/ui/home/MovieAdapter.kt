package com.bahakuzudisli.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bahakuzudisli.movieapp.databinding.ItemHomeRecyclerViewBinding
import com.bahakuzudisli.movieapp.model.MovieItem
import com.bahakuzudisli.movieapp.util.loadCircleImage

interface MovieClickListener {
    fun onMovieClicked(movieId: Int?)
}

class MovieAdapter(
    private val movieList: List<MovieItem?>,
    private val movieClickListener: MovieClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = ItemHomeRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]

        holder.binding.textViewMovieTitle.text = movie?.title
        holder.binding.textViewMovieOverview.text = movie?.overview
        holder.binding.imageViewMovie.loadCircleImage(movie?.posterPath)
        val voteAverage: Double? = movie?.voteAverage
        if (voteAverage != null) {
            val formattedVote = String.format("%.1f", voteAverage)
            holder.binding.textViewMovieVote.text = formattedVote
        } else {
            // Handle null case, if needed
            holder.binding.textViewMovieVote.text = "N/A"
        }

        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movie?.id)
        }

    }
}