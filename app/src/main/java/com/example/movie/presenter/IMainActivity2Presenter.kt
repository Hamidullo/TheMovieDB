package com.example.movie.presenter

interface IMainActivity2Presenter {
    fun loadActor(actorId: String)
    fun loadActorCast(actorId: String)
    fun cancel()
}