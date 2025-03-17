package com.example.core.data.mapper

import com.example.core.domain.model.Genre
import com.example.core.data.remote.model.Genre as DataGenre

object GenreMapper {
    fun DataGenre.mapToDomain(): Genre {
        return Genre(
            id = this.id,
            name = this.name
        )
    }
}
