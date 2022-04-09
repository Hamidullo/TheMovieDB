package com.example.movie.model.model

import com.example.movie.model.api.Api
import com.example.movie.model.movie.Movie
import com.example.movie.model.moviecast.MovieCast
import com.example.movie.model.popular_top.MovieList
import com.example.movie.model.upcoming.UpcomingList

class MovieListRepository(private val api: Api) : IMovieListRepository {

    override suspend fun loadPopular(): MovieList = api.loadPopular()
    override suspend fun loadTopRated(): MovieList = api.loadTopRated()
    override suspend fun loadUpcoming(): UpcomingList = api.loadUpcoming()
    override suspend fun loadMovie(movieId: String): Movie = api.loadMovie(movieId)
    override suspend fun loadMovieCast(movieId: String): MovieCast = api.loadMovieCast(movieId)

    companion object {
        private var instance: MovieListRepository? = null
        operator fun invoke(): IMovieListRepository {
            var localIns = instance
            if (localIns == null) {
                val api = Api()
                localIns = MovieListRepository(api)
                instance = localIns
            }
            return localIns
        }
    }
}