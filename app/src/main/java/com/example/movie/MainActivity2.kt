package com.example.movie

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.movie.databinding.ActivityMain2Binding
import com.example.movie.ui2.ActorFragment
import com.example.movie.ui2.MovieFragment


class MainActivity2 : AppCompatActivity(), MovieFragment.OnFragmentSendDataListener, ActorFragment.OnFragmentSendDataMovieListener {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //presenter = MainActivityPresenter(this)

        //makeTextViewResizable(binding.biographyActorDetail, 17, "View More", true);
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.detail_graph)
        return navController.navigateUp()
    }

    override fun onSendData(data: String?) {
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("MovieDBAPP",
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("castID", data)
        editor.apply()
        editor.commit()
        val navController: NavController =
            Navigation.findNavController(this@MainActivity2, R.id.movieFragment)
        navController.navigate(R.id.action_movieFragment2_to_actorFragment)
    }

    override fun onSendMovieData(data: String?) {
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("MovieDBAPP",
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("movieID", data)
        editor.apply()
        editor.commit()
        val navController: NavController =
            Navigation.findNavController(this@MainActivity2, R.id.movieFragment)
        navController.navigate(R.id.action_actorFragment_to_movieFragment2)
    }

    /*override fun onResume() {
        super.onResume()
        presenter?.loadMovie(movieID)
    }

    override fun onStop() {
        super.onStop()
        presenter?.cancel()
    }

    override fun sendPopularList(movieList: MovieList) {
        println(movieList.results)
    }

    override fun sendTopRatedList(movieList: MovieList) {
        println(movieList.results)
    }

    override fun sendUpcomingList(movieList: UpcomingList) {
        println(movieList.results)
    }

    override fun sendMovie(movie: Movie) {
        println(movie)

        val backPosterUrl = "https://image.tmdb.org/t/p/w500${movie.backdropPath}"
        Picasso.get().load(backPosterUrl).error(R.drawable.error).into(binding.backdropPathDetail)
        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Picasso.get().load(posterUrl).error(R.drawable.error).into(binding.posterPathDetail)
        binding.originalTitleDetail.text = movie.originalTitle
        binding.overviewMovieDetail.text = movie.overview
        binding.releaseDateDetail.text = movie.releaseDate
        binding.budgetMovieDetail.text = "$${movie.budget}"
        binding.voteAverageDetail.text = movie.voteAverage.toString()
        binding.revenueMovieDetail.text = "$${movie.revenue}"
        binding.voteCountDetail.text = movie.voteCount.toString()
        binding.popularityMovieDetail.text = movie.popularity.toString()
        adapterGenres = AdapterGenres(movie.genres)
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.genresListDetail.adapter = adapterGenres
        binding.genresListDetail.layoutManager = layoutManager
        presenter!!.loadMovieCast(movieID)
    }

    override fun sendActor(actor: Actor) {
        println(actor)
    }

    override fun sendMovieCast(movieCast: MovieCast) {
        println(movieCast.cast)
        adapterCast = AdapterCast(movieCast.cast)
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.castListDetail.adapter = adapterCast
        binding.castListDetail.layoutManager = layoutManager
        println(movieCast.cast[0].id.toString())
        presenter!!.loadActor(movieCast.cast[0].id.toString())
    }

    override fun onFail() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Fail")
            .setMessage("Something went wrong please try again")
            .create()
        alertDialog.show()
    }

    override fun showRefresh() {
        binding.containerMain.visibility = View.GONE
        binding.loadingIndicator.visibility = View.VISIBLE
    }

    override fun hideRefresh() {
        binding.containerMain.visibility = View.VISIBLE//View.GONE
        binding.loadingIndicator.visibility = View.GONE//View.VISIBLE
    }*/
}