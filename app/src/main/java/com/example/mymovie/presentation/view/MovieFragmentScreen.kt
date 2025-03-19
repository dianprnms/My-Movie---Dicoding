package com.example.mymovie.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.R
import com.example.core.ui.NowPlayingAdapter
import com.example.mymovie.databinding.FragmentMovieBinding
import com.example.mymovie.presentation.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragmentScreen : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val nowPlayingViewModel: NowPlayingViewModel by viewModels()
    private lateinit var nowPlayingAdapter: NowPlayingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingAdapter = NowPlayingAdapter(emptyList())

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMovie.layoutManager = layoutManager
        binding.rvMovie.adapter = nowPlayingAdapter

        observeViewModel()

        val genreId = arguments?.getInt("genreId", -1)
        if (genreId != null && genreId != -1) {
            fetchData(genreId)
        }
    }

    private fun observeViewModel() {
        // Collecting Flow in lifecycleScope
        lifecycleScope.launch {
            nowPlayingViewModel.nowPlayingData.collect { movies ->
                if (movies != null) {
                delay(3000)
                    binding.shimmer.visibility = View.GONE
                    binding.rvMovie.visibility = View.VISIBLE

                    nowPlayingAdapter.updateResult(movies)
                }
            }
        }

        nowPlayingAdapter.onClick = { selectedMovie ->
            val bundle = Bundle().apply {
                putInt("movieId", selectedMovie.id!!.toInt())
            }
            findNavController().navigate(R.id.action_movieFragment_to_detailFragment, bundle)
        }
    }

    private fun fetchData(genreId: Int) {
        lifecycleScope.launch {
            nowPlayingViewModel.callApiNowPlayingByGenre(genreId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvMovie.adapter = null
        _binding = null
    }
}
