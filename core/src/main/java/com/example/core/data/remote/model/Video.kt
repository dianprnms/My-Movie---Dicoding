package com.example.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<ResultXXX>?
)