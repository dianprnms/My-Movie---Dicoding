package com.example.core.data.network

import com.example.core.data.remote.model.Detail
import com.example.core.data.remote.model.Genres
import com.example.core.data.remote.model.Movie
import com.example.core.data.remote.model.Reviews
import com.example.core.data.remote.model.Video
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmAPIService {

    @GET("genre/movie/list?api_key=ca0d93ea30b7015390f9ae8d859f6863")
    suspend fun getMovieGenres(): Genres

    @GET("movie/now_playing?api_key=ca0d93ea30b7015390f9ae8d859f6863")
    suspend fun getMovieNowPlaying(): Movie

    @GET("discover/movie?api_key=ca0d93ea30b7015390f9ae8d859f6863")
    suspend fun getMoviesByGenre(@Query("with_genres") genreId: Int): Movie

    @GET("movie/{movieId}?api_key=ca0d93ea30b7015390f9ae8d859f6863")
    suspend fun getMovieDetail(@Path("movieId") movieId:Int): Detail

    @GET("movie/{movieId}/reviews?api_key=ca0d93ea30b7015390f9ae8d859f6863")
    suspend fun getReviewsMovie(@Path("movieId") movieId: Int): Reviews

    @GET("movie/{movieId}/videos?api_key=ca0d93ea30b7015390f9ae8d859f6863")
    suspend fun getVideo(@Path("movieId") movieId: Int): Video

}
