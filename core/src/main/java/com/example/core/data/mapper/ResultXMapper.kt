package com.example.core.data.mapper

import com.example.core.data.remote.model.ResultXModel as DataResult
import com.example.core.domain.model.ResultX

object ResultXMapper {
    fun DataResult.mapToDomain(): ResultX {
        return ResultX(
            id = this.id,
            title = this.title,
            posterPath = this.posterPath,
            overview = this.overview,
            releaseDate = this.releaseDate,
            adult = this.adult,
            backdropPath = this.backdropPath,
            genreIds = this.genreIds,
            originalLanguage = this.originalLanguage,
            originalTitle = this.originalTitle,
            popularity = this.popularity,
            video = this.video,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        )
    }
}
