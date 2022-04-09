package com.example.movie.presenter

import com.example.movie.model.model.ActorRepository
import com.example.movie.model.model.IActorRepository
import com.example.movie.model.model.IMovieListRepository
import com.example.movie.model.model.MovieListRepository
import com.example.movie.view.IMainActivityView
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivityPresenter(private val view: IMainActivityView) : IMainActivityPresenter,
    CoroutineScope {
    private val repository: IMovieListRepository = MovieListRepository()
    private val mainJob = SupervisorJob()

    override fun loadPopular() {
        launch(Dispatchers.Main) {
            view.showRefresh()
            try {
                val movieListTemp = withContext(Dispatchers.IO) {
                    repository.loadPopular()
                }
                view.sendPopularList(movieListTemp)
                view.hideRefresh()
            } catch (e: Exception) {
                e.printStackTrace()
                view.onFail()
            } finally {
                view.hideRefresh()
            }
        }
    }

    override fun loadTopRated() {
        launch(Dispatchers.Main) {
            view.showRefresh()
            try {
                val movieListTemp = withContext(Dispatchers.IO) {
                    repository.loadTopRated()
                }
                view.sendTopRatedList(movieListTemp)
                view.hideRefresh()
            } catch (e: Exception) {
                e.printStackTrace()
                view.onFail()
            } finally {
                view.hideRefresh()
            }
        }
    }

    override fun loadUpcoming() {
        launch(Dispatchers.Main) {
            view.showRefresh()
            try {
                val movieListTemp = withContext(Dispatchers.IO) {
                    repository.loadUpcoming()
                }
                view.sendUpcomingList(movieListTemp)
                view.hideRefresh()
            } catch (e: Exception) {
                e.printStackTrace()
                view.onFail()
            } finally {
                view.hideRefresh()
            }
        }
    }

    override fun loadMovie(movieId: String) {
        launch(Dispatchers.Main) {
            view.showRefresh()
            try {
                val movieListTemp = withContext(Dispatchers.IO) {
                    repository.loadMovie(movieId)
                }
                view.sendMovie(movieListTemp)
                view.hideRefresh()
            } catch (e: Exception) {
                e.printStackTrace()
                view.onFail()
            } finally {
                view.hideRefresh()
            }
        }
    }

    override fun loadMovieCast(movieId: String) {
        launch(Dispatchers.Main) {
            view.showRefresh()
            try {
                val movieListTemp = withContext(Dispatchers.IO) {
                    repository.loadMovieCast(movieId)
                }
                view.sendMovieCast(movieListTemp)
                view.hideRefresh()
            } catch (e: Exception) {
                e.printStackTrace()
                view.onFail()
            } finally {
                view.hideRefresh()
            }
        }
    }

    override fun cancel() {
        mainJob.cancel()
    }

    override val coroutineContext: CoroutineContext = mainJob
}