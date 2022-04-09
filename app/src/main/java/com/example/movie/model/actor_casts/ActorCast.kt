package com.example.movie.model.actor_casts


import com.google.gson.annotations.SerializedName

data class ActorCast(
    @SerializedName("cast")
    val cast: List<Cast> = listOf(),
    @SerializedName("crew")
    val crew: List<Crew> = listOf(),
    @SerializedName("id")
    val id: Int = 0
)