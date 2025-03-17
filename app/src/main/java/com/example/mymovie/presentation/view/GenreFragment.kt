package com.example.mymovie.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.R
import com.example.core.ui.GenreAdapter
import com.example.mymovie.databinding.FragmentGenreBinding
import com.example.mymovie.presentation.viewmodel.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenreFragment : Fragment() {

    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var genreViewModel: GenreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genreViewModel = ViewModelProvider(this)[GenreViewModel::class.java]
        genreAdapter = GenreAdapter(emptyList())

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvGenre.layoutManager = layoutManager
        binding.rvGenre.adapter = genreAdapter

        fetchData()

        binding.icFav.setOnClickListener {
            findNavController().navigate(R.id.action_genre_to_favoriteMoviesFragment)
        }

        observeGenreFlow()
    }

    private fun fetchData() {
        genreViewModel.callApiGenres()
    }

    private fun observeGenreFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                genreViewModel.genreFlow.collect { genres ->
                    if (genres != null) {
                        delay(3000)

                        binding.shimmer.visibility = View.GONE
                        binding.rvGenre.visibility = View.VISIBLE

                        genreAdapter.updateGenres(genres)
                    }
                }
            }
        }

        genreAdapter.onClick = { selectedGenre ->
            val bundle = Bundle().apply {
                putInt("genreId", selectedGenre.id!!.toInt())
            }
            findNavController().navigate(R.id.action_genre_to_movieFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



