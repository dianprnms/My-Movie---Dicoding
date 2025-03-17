package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.domain.model.MovieDataDomain

class FavoriteMoviesAdapter(private var movies: List<MovieDataDomain>, private val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<FavoriteMoviesAdapter.FavoriteMoviesViewHolder>() {

    inner class FavoriteMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.imgMovie)
        val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        val movieDate: TextView = itemView.findViewById(R.id.movieDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_movie, parent, false)
        return FavoriteMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieTitle.text = movie.title
        holder.movieDate.text = movie.releaseDate
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.movieImage)

        holder.itemView.setOnClickListener {
            onItemClick(movie.id)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(movies: List<MovieDataDomain>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}
