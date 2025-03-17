package com.example.core.data.mapper

import com.example.core.data.local.room.MovieEntity
import com.example.core.domain.model.MovieDataDomain

object MovieMapper {
    // Map MovieEntity to MovieDataDomain
    fun MovieEntity.toMovieDataDomain(): MovieDataDomain {
        return MovieDataDomain(
            id = this.id,
            title = this.title,
            releaseDate = this.releaseDate,
            posterPath = this.posterPath
        )
    }

    fun MovieDataDomain.toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = this.id,
            title = this.title,
            releaseDate = this.releaseDate,
            posterPath = this.posterPath
        )
    }
}
