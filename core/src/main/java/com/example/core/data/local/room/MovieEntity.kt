package com.example.core.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieData")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var releaseDate: String,
    var posterPath: String
)

