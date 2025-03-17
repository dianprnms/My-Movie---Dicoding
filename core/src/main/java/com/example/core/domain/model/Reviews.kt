package com.example.core.domain.model

import com.google.gson.annotations.SerializedName

data class Reviews(
    val id: Int?,
    val page: Int?,
    val results: List<ResultXX>?,
    val totalPages: Int?,
    val totalResults: Int?
)