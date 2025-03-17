package com.example.core.domain.model

data class Genre(
    val id: Int?,
    val name: String?
)

fun Genre.toDomainModel(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}