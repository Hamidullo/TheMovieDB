package com.example.movie.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.adapter.AdapterPopular
import com.example.movie.model.popular_top.Result

class TopRatedFragment : Fragment() {

    companion object {
        fun newInstance() = TopRatedFragment()
    }

    private lateinit var viewModel: TopRatedViewModel
    private lateinit var adapter: AdapterPopular
    private lateinit var movieListR: RecyclerView
    private lateinit var loading: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.top_rated_fragment, container, false)
        movieListR = view.findViewById(R.id.movieListTR)
        loading = view.findViewById(R.id.loadingIndicatorT)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[TopRatedViewModel::class.java]
        loading.visibility = View.VISIBLE
        viewModel.getMovies()!!.observe(viewLifecycleOwner,
            Observer<List<Result?>?> { movieList ->
                adapter = AdapterPopular(movieList, requireContext())
                movieListR.adapter = adapter
                loading.visibility = View.GONE
            })
    }

    override fun onStop() {
        super.onStop()
        viewModel.cancel()
    }
}