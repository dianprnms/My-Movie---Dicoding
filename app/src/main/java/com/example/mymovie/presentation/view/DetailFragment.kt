package com.example.mymovie.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.core.Constants
import com.example.mymovie.R
import com.example.core.ui.ReviewsAdapter
import com.example.mymovie.databinding.FragmentDetailBinding
import com.example.mymovie.presentation.viewmodel.DetailViewModel
import com.example.mymovie.presentation.viewmodel.ReviewsViewModel
import com.example.mymovie.presentation.viewmodel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit private var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val reviewsViewModel: ReviewsViewModel by viewModels()
    private val videoViewModel: VideoViewModel by viewModels()
    private lateinit var reviewsAdapter: ReviewsAdapter
    private var videoKey: String? = null
    private var movieId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = arguments?.getInt(Constants.KEY.ARG_MOVIE_ID)
        movieId?.let {
            fetchData(it)
            fetchReviews(it)
            fetchVideo(it)
            checkIfFavorited(it)
        }

        reviewsAdapter = ReviewsAdapter(emptyList())
        setupRecyclerView()

        observeDetailData()
        observeReviewsData()
        observeVideoData()
        observeFavoriteStatus()

        binding.icFav.setOnClickListener {
            movieId?.let { id ->
                detailViewModel.toggleFavoriteStatus(id)
            }
        }
    }

    private fun checkIfFavorited(movieId: Int) {
        detailViewModel.checkIfFavorited(movieId)
    }

    private fun setupRecyclerView() {
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reviewsAdapter
        }
    }

    private fun fetchData(movieId: Int) {
        detailViewModel.getMovieDetail(movieId)
    }

    private fun observeDetailData() {
        lifecycleScope.launch {
            detailViewModel.detailData.collect { detail ->
                detail?.let {
                    binding.judulDetail.text = it.title
                    binding.textDetail.text = it.overview
                    Glide.with(this@DetailFragment)
                        .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                        .into(binding.imageDetail)
                }
            }
        }
    }

    private fun fetchReviews(movieId: Int) {
        reviewsViewModel.getReviews(movieId)
    }

    private fun observeReviewsData() {
        lifecycleScope.launch {
            reviewsViewModel.reviewsData.collect { reviews ->
                reviews?.let {
                    if (it.results.isNullOrEmpty()) {
                        binding.tidakAdaReview.visibility = View.VISIBLE
                        binding.rvMovie.visibility = View.GONE
                    } else {
                        binding.tidakAdaReview.visibility = View.GONE
                        binding.rvMovie.visibility = View.VISIBLE
                        reviewsAdapter.updateReviews(it.results!!)
                    }
                }
            }
        }
    }

    private fun fetchVideo(movieId: Int) {
        videoViewModel.getVideo(movieId)
    }

    private fun observeVideoData() {
        lifecycleScope.launch {
            videoViewModel.videoData.collect { videoResults ->
                videoResults?.let {
                    if (it.isNotEmpty()) {
                        videoKey = it[0].key
                        loadYouTubeVideo()
                        binding.tdkAdaVideo.visibility = View.GONE
                        binding.webViewSaya.visibility = View.VISIBLE
                    } else {
                        binding.tdkAdaVideo.visibility = View.VISIBLE
                        binding.webViewSaya.visibility = View.GONE
                    }
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadYouTubeVideo() {
        val youtubeVideoUrl = "https://www.youtube.com/embed/$videoKey"
        binding.webViewSaya.settings.javaScriptEnabled = true
        binding.webViewSaya.webChromeClient = WebChromeClient()
        binding.webViewSaya.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        binding.webViewSaya.loadUrl(youtubeVideoUrl)
    }

    private fun observeFavoriteStatus() {
        lifecycleScope.launch {
            detailViewModel.isFavorited.collect { isFavorited ->
                binding.icFav.setImageResource(
                    if (isFavorited) R.drawable.baseline_favorite_24
                    else R.drawable.baseline_favorite_border_24
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding
    }
}
