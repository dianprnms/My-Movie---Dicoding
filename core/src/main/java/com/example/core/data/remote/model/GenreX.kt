package com.example.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenreX(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)