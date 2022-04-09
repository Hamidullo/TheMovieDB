package com.example.movie.model.moviecast


import com.google.gson.annotations.SerializedName

data class MovieCast(
    @SerializedName("cast")
    val cast: List<Cast> = listOf(),
    @SerializedName("crew")
    val crew: List<Crew> = listOf(),
    @SerializedName("id")
    val id: Int = 0
)