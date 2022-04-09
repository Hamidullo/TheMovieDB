package com.example.movie.model.api

import com.example.movie.model.actor.Actor
import com.example.movie.model.actor_casts.ActorCast
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL_ACTOR = "https://api.themoviedb.org/3/person/"

interface ApiActor {

    @GET("{actorId}")
    suspend fun loadActor(
        @Path("actorId") actorId: String,
        @Query("api_key") api_key: String = KEY,
        @Query("language") language: String = "en-US"
    ): Actor

    @GET("{actorId}/movie_credits")
    suspend fun loadActorCast(
        @Path("actorId") actorId: String,
        @Query("api_key") api_key: String = KEY,
        @Query("language") language: String = "en-US"
    ): ActorCast

    companion object {
        operator fun invoke(): ApiActor {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_ACTOR)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApiActor::class.java)
        }
    }
}