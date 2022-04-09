package com.example.movie.model.model

import com.example.movie.model.actor.Actor
import com.example.movie.model.actor_casts.ActorCast
import com.example.movie.model.movie.Movie
import com.example.movie.model.moviecast.MovieCast
import com.example.movie.model.popular_top.MovieList
import com.example.movie.model.upcoming.UpcomingList

interface IActorRepository {
    suspend fun loadActor(actorId: String): Actor
    suspend fun loadActorCast(actorId: String): ActorCast
}