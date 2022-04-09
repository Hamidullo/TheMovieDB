package com.example.movie.model.model

import com.example.movie.model.actor.Actor
import com.example.movie.model.movie.Movie
import com.example.movie.model.moviecast.MovieCast
import com.example.movie.model.popular_top.MovieList
import com.example.movie.model.upcoming.UpcomingList

interface IMovieListRepository {
    suspend fun loadPopular(): MovieList
    suspend fun loadTopRated(): MovieList
    suspend fun loadUpcoming(): UpcomingList
    suspend fun loadMovie(movieId: String): Movie
    suspend fun loadMovieCast(movieId: String): MovieCast
}