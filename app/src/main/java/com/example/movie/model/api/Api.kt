package com.example.movie.model.api

import com.example.movie.model.actor.Actor
import com.example.movie.model.movie.Movie
import com.example.movie.model.moviecast.MovieCast
import com.example.movie.model.popular_top.MovieList
import com.example.movie.model.upcoming.UpcomingList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/movie/"
const val KEY = "4920dc2406688fc83dde76bf7a312827"

interface Api {
    @GET("popular")
    suspend fun loadPopular(
        @Query("api_key") api_key: String = KEY,
        @Query("language") language: String = "en-US"
    ): MovieList

    @GET("top_rated")
    suspend fun loadTopRated(
        @Query("api_key") api_key: String = KEY,
        @Query("language") language: String = "en-US"
    ): MovieList

    @GET("upcoming")
    suspend fun loadUpcoming(
        @Query("api_key") api_key: String = KEY,
        @Query("language") language: String = "en-US"
    ): UpcomingList

    @GET("{movieId}")
    suspend fun loadMovie(
        @Path("movieId") movieId: String,
        @Query("api_key") api_key: String = KEY,
        @Query("language") language: String = "en-US"
    ): Movie

    @GET("{movieId}/credits")
    suspend fun loadMovieCast(
        @Path("movieId") movieId: String,
        @Query("api_key") api_key: String = KEY,
        @Query("language") language: String = "en-US"
    ): MovieCast

    companion object {
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(Api::class.java)
        }
    }
}