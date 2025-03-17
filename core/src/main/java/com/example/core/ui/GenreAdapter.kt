package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemGenreBinding
import com.example.core.domain.model.Genre

class GenreAdapter (private var listGenre: List<Genre>):RecyclerView.Adapter<GenreAdapter.ViewHolder>()  {
    var onClick : ((Genre)->Unit)? = null

    class ViewHolder (var binding: ItemGenreBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listGenre.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.listGenre.text = listGenre[position].name
        holder.binding.itemGenre.setOnClickListener {
            onClick!!.invoke(listGenre[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGenres(newGenres: List<Genre>) {
        listGenre = newGenres
        notifyDataSetChanged()
    }
}
