package com.example.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var releaseDate: String,
    var posterPath: String
)
