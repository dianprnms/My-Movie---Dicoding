package com.example.core.domain.model

data class ReviewDomain(
    val id: String,
    val author: String,
    val content: String,
    val url: String
)

data class ReviewsDomain(
    val results: List<ReviewDomain>?
)