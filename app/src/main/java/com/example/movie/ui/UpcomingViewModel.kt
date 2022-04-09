package com.example.movie.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie.model.model.IMovieListRepository
import com.example.movie.model.model.MovieListRepository
import com.example.movie.model.upcoming.Result
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UpcomingViewModel : ViewModel() , CoroutineScope {
    private val repository: IMovieListRepository = MovieListRepository()
    private val mainJob = SupervisorJob()
    private var movieList: MutableLiveData<List<Result>>? = null

    fun getMovies(): MutableLiveData<List<Result>>? {
        //if the list is null
        if (movieList == null) {
            movieList = MutableLiveData<List<Result>>()
            //we will load it asynchronously from server in this method
            loadUpcoming()
        }
        //finally we will return the list
        return movieList
    }

    private fun loadUpcoming() {
        launch(Dispatchers.Main) {
            try {
                val movieListTemp = withContext(Dispatchers.IO) {
                    repository.loadUpcoming()
                }
                movieList!!.value = movieListTemp.results
            } catch (e: Exception) {
                e.printStackTrace()

            } finally {

            }
        }
    }

    fun cancel() {
        mainJob.cancel()
    }

    override val coroutineContext: CoroutineContext = mainJob
}