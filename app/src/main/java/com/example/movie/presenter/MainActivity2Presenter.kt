package com.example.movie.presenter

import com.example.movie.model.model.ActorRepository
import com.example.movie.model.model.IActorRepository
import com.example.movie.model.model.IMovieListRepository
import com.example.movie.model.model.MovieListRepository
import com.example.movie.view.IMainActivity2View
import com.example.movie.view.IMainActivityView
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity2Presenter(private val view: IMainActivity2View) : IMainActivity2Presenter,
    CoroutineScope {

    private val repositoryActor: IActorRepository = ActorRepository()
    private val mainJob = SupervisorJob()

    override fun loadActor(actorId: String) {
        launch(Dispatchers.Main) {
            view.showRefresh()
            try {
                val actorTemp = withContext(Dispatchers.IO) {
                    repositoryActor.loadActor(actorId)
                }
                view.sendActor(actorTemp)
                view.hideRefresh()
            } catch (e: Exception) {
                e.printStackTrace()
                view.onFail()
            } finally {
                view.hideRefresh()
            }
        }
    }

    override fun loadActorCast(actorId: String) {
        launch(Dispatchers.Main) {
            view.showRefresh()
            try {
                val actorTemp = withContext(Dispatchers.IO) {
                    repositoryActor.loadActorCast(actorId)
                }
                view.sendActorCast(actorTemp)
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