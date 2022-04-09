package com.example.movie.view

import com.example.movie.model.actor.Actor
import com.example.movie.model.movie.Movie
import com.example.movie.model.moviecast.MovieCast
import com.example.movie.model.popular_top.MovieList
import com.example.movie.model.upcoming.UpcomingList

interface IMainActivityView {
    fun sendPopularList(movieList: MovieList)
    fun sendTopRatedList(movieList: MovieList)
    fun sendUpcomingList(movieList: UpcomingList)
    fun sendMovie(movie: Movie)
    fun sendActor(actor: Actor)
    fun sendMovieCast(movieCast: MovieCast)
    fun onFail()
    fun showRefresh()
    fun hideRefresh()
}