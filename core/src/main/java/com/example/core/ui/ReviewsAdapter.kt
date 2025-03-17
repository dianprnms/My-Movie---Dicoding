package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemReviewsBinding
import com.example.core.domain.model.ReviewDomain

// Contoh sederhana dari Adapter yang menerima domain model
class ReviewsAdapter(private var reviews: List<ReviewDomain>) : RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int = reviews.size

    fun updateReviews(newReviews: List<ReviewDomain>) {
        reviews = newReviews
        notifyDataSetChanged()
    }

    class ReviewViewHolder(private val binding: ItemReviewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: ReviewDomain) {
            binding.author.text = review.author
            binding.review.text = review.content
        }
    }
}
