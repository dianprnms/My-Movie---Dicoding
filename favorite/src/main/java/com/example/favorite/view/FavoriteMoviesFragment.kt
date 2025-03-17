package com.example.favorite.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.FavoriteMoviesAdapter
import com.example.favorite.databinding.FragmentFavoriteMoviesBinding
import com.example.core.dependencies.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch

class FavoriteMoviesFragment : Fragment() {
    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter
    private lateinit var favoriteMoviesViewModel: FavoriteMoviesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            FavoriteModuleDependencies::class.java
        )

        val factory = FavoriteMoviesViewModelFactory(entryPoint.provideMovieRepository())
        favoriteMoviesViewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onItemClick: (Int) -> Unit = { movieId ->
            val bundle = Bundle().apply {
                putInt("movieId", movieId)
            }
            findNavController().navigate(com.example.mymovie.R.id.action_favoriteMoviesFragment_to_detailFragment, bundle)
        }

        favoriteMoviesAdapter = FavoriteMoviesAdapter(emptyList(), onItemClick)
        binding.rvFavoriteMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteMovies.adapter = favoriteMoviesAdapter

        lifecycleScope.launch {
            favoriteMoviesViewModel.favoriteMovies.collect { movies ->
                _binding?.let { binding ->
                    if (movies.isEmpty()) {
                        binding.tvNoFavorites.visibility = View.VISIBLE
                        binding.rvFavoriteMovies.visibility = View.GONE
                    } else {
                        binding.tvNoFavorites.visibility = View.GONE
                        binding.rvFavoriteMovies.visibility = View.VISIBLE
                        favoriteMoviesAdapter.updateMovies(movies)
                    }
                }
            }
        }

        favoriteMoviesViewModel.fetchFavoriteMovies()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
