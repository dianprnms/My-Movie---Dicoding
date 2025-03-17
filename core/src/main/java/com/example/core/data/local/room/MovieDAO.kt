package com.example.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert
    fun insertData(movieData: MovieEntity)

    @Query("SELECT * FROM MovieData WHERE id = :movieId")
    fun getMovieById(movieId: Int): Flow<MovieEntity?>

    @Query("DELETE FROM MovieData WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: Int)

    @Query("SELECT * FROM MovieData")
    fun getAllMovies(): Flow<List<MovieEntity>>

}