package com.example.core.data.mapper

import com.example.core.data.mapper.DataBelongsToCollectionMapper.toDomainModel
import com.example.core.data.mapper.DataProductionCompanyMapper.toDomainModel
import com.example.core.data.mapper.DataProductionCountriesMapper.toDomainModel
import com.example.core.data.mapper.GenreMapper.mapToDomain
import com.example.core.data.mapper.SpokenLanguageMapper.toDomainModel
import com.example.core.domain.model.MovieDetail
import com.example.core.data.remote.model.Detail

object DetailMapper {
    fun Detail.toDomainModel(): MovieDetail {
        return MovieDetail(
            adult = this.adult,
            backdropPath = this.backdropPath,
            belongsToCollection = this.belongsToCollection.toDomainModel(),
            budget = this.budget,
            genres = this.genres?.map { it.mapToDomain() },
            homepage = this.homepage,
            id = this.id,
            imdbId = this.imdbId,
            originalLanguage = this.originalLanguage,
            originalTitle = this.originalTitle,
            overview = this.overview,
            popularity = this.popularity,
            posterPath = this.posterPath,
            productionCompanies = this.productionCompanies?.map { it.toDomainModel() },
            productionCountries = this.productionCountries?.map { it.toDomainModel() },
            releaseDate = this.releaseDate,
            revenue = this.revenue,
            runtime = this.runtime,
            spokenLanguages = this.spokenLanguages?.map { it.toDomainModel() },
            status = this.status,
            tagline = this.tagline,
            title = this.title,
            video = this.video,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        )
    }
}
