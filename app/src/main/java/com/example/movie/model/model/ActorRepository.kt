package com.example.movie.model.model

import com.example.movie.model.actor.Actor
import com.example.movie.model.actor_casts.ActorCast
import com.example.movie.model.api.Api
import com.example.movie.model.api.ApiActor
import com.example.movie.model.movie.Movie
import com.example.movie.model.moviecast.MovieCast
import com.example.movie.model.popular_top.MovieList
import com.example.movie.model.upcoming.UpcomingList

class ActorRepository(private val api: ApiActor) : IActorRepository {

    override suspend fun loadActor(actorId: String): Actor = api.loadActor(actorId)
    override suspend fun loadActorCast(actorId: String): ActorCast = api.loadActorCast(actorId)

    companion object {
        private var instance: ActorRepository? = null
        operator fun invoke(): IActorRepository {
            var localIns = instance
            if (localIns == null) {
                val api = ApiActor()
                localIns = ActorRepository(api)
                instance = localIns
            }
            return localIns
        }
    }


}