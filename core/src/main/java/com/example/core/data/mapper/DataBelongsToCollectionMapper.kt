package com.example.core.data.mapper

import com.example.core.domain.model.BelongsToCollection
import com.example.core.data.remote.model.BelongsToCollection as DataBelongsToCollection

object DataBelongsToCollectionMapper {
    fun DataBelongsToCollection?.toDomainModel(): BelongsToCollection? {
        return this?.let {
            BelongsToCollection(
                id = it.id,
                name = it.name,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath
            )
        }
    }
}
