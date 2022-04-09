package com.example.movie.presenter

interface IMainActivityPresenter {
    fun loadPopular()
    fun loadTopRated()
    fun loadUpcoming()
    fun loadMovie(movieId: String)
    fun loadMovieCast(movieId: String)
    fun cancel()
}