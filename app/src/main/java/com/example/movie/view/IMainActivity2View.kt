package com.example.movie.view

import com.example.movie.model.actor.Actor
import com.example.movie.model.actor_casts.ActorCast
import com.example.movie.model.movie.Movie
import com.example.movie.model.moviecast.MovieCast
import com.example.movie.model.popular_top.MovieList
import com.example.movie.model.upcoming.UpcomingList

interface IMainActivity2View {
    fun sendActor(actor: Actor)
    fun sendActorCast(actorCast: ActorCast)
    fun onFail()
    fun showRefresh()
    fun hideRefresh()
}