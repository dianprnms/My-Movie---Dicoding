package com.example.core.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val dates: Dates?,
    val page: Int?,
    val results: List<ResultXModel>?,
    val totalPages: Int?,
    val totalResults: Int?
)