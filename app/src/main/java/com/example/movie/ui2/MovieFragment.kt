package com.example.movie.ui2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.adapter.AdapterCast
import com.example.movie.adapter.AdapterGenres
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.model.actor.Actor
import com.example.movie.model.movie.Movie
import com.example.movie.model.moviecast.Cast
import com.example.movie.model.moviecast.MovieCast
import com.example.movie.model.popular_top.MovieList
import com.example.movie.model.upcoming.UpcomingList
import com.example.movie.presenter.IMainActivityPresenter
import com.example.movie.presenter.MainActivityPresenter
import com.example.movie.view.IMainActivityView
import com.squareup.picasso.Picasso

class MovieFragment : Fragment(), IMainActivityView {

    private lateinit var binding: FragmentMovieBinding
    private var presenter: IMainActivityPresenter? = null
    private var movieID: String = "634649"
    private lateinit var adapterGenres: AdapterGenres
    private lateinit var adapterCast: AdapterCast

    internal interface OnFragmentSendDataListener {
        fun onSendData(data: String?)
    }

    private lateinit var fragmentSendDataListener: OnFragmentSendDataListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentSendDataListener = try {
            context as OnFragmentSendDataListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context должен реализовывать интерфейс OnFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainActivityPresenter(this)

        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences("MovieDBAPP",
            Context.MODE_PRIVATE)
        movieID = sharedPreferences.getString("movieID","634649")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        binding = FragmentMovieBinding.bind(view)

        return view
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
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.genresListDetail.adapter = adapterGenres
        binding.genresListDetail.layoutManager = layoutManager
        presenter!!.loadMovieCast(movieID)
    }

    override fun sendActor(actor: Actor) {
        println(actor)
    }

    override fun sendMovieCast(movieCast: MovieCast) {
        println(movieCast.cast)
        val castClickListener: AdapterCast.OnCastClickListener =
            object : AdapterCast.OnCastClickListener {
                override fun onCastClick(cast: Cast?, position: Int) {
                    // Посылаем данные Activity
                    fragmentSendDataListener.onSendData(cast!!.id.toString())
                }
            }
        adapterCast = AdapterCast(movieCast.cast,castClickListener)
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.castListDetail.adapter = adapterCast
        binding.castListDetail.layoutManager = layoutManager
        println(movieCast.cast[0].id.toString())
        //presenter!!.loadActor(movieCast.cast[0].id.toString())
    }

    override fun onFail() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Fail")
            .setMessage("Something went wrong please try again")
            .create()
        alertDialog.show()
    }

    override fun showRefresh() {
        binding.containerMainM.visibility = View.GONE
        binding.loadingIndicatorM.visibility = View.VISIBLE
    }

    override fun hideRefresh() {
        binding.containerMainM.visibility = View.VISIBLE//View.GONE
        binding.loadingIndicatorM.visibility = View.GONE//View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        presenter?.loadMovie(movieID)
        println("onResume")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.cancel()
        println("onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        println("onDetach")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("onSaveInstanceState")
    }

    override fun onStart() {
        super.onStart()
        println("onStart")
    }
}