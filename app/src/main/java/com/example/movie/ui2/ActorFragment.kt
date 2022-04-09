package com.example.movie.ui2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.adapter.AdapterMovies
import com.example.movie.databinding.FragmentActorBinding
import com.example.movie.model.actor.Actor
import com.example.movie.model.actor_casts.ActorCast
import com.example.movie.model.actor_casts.Cast
import com.example.movie.presenter.MainActivity2Presenter
import com.example.movie.utils.makeTextViewResizable
import com.example.movie.view.IMainActivity2View
import com.squareup.picasso.Picasso

class ActorFragment : Fragment(), IMainActivity2View {

    private lateinit var binding: FragmentActorBinding
    private var castId: String = "71580"
    private var presenter: MainActivity2Presenter? = null
    private lateinit var biography: TextView
    private lateinit var adapterMovie: AdapterMovies

    internal interface OnFragmentSendDataMovieListener {
        fun onSendMovieData(data: String?)
    }

    private lateinit var fragmentSendMovieDataListener: ActorFragment.OnFragmentSendDataMovieListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentSendMovieDataListener = try {
            context as ActorFragment.OnFragmentSendDataMovieListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context должен реализовывать интерфейс OnFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainActivity2Presenter(this)

        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences("MovieDBAPP",
            Context.MODE_PRIVATE)
        castId = sharedPreferences.getString("castID","71580")!!
        println(castId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_actor, container, false)
        binding = FragmentActorBinding.bind(view)
        biography = view.findViewById(R.id.biographyActorDetail)
        return view
    }

    override fun sendActor(actor: Actor) {
        println(actor)
        val backPosterUrl = "https://image.tmdb.org/t/p/w500${actor.profilePath}"
        Picasso.get().load(backPosterUrl).error(R.drawable.error).into(binding.profileActorDetail)

        binding.nameActorDetail.text = actor.name
        if (actor.alsoKnownAs.isNotEmpty()){
            binding.fullNameActorDetail.text = actor.alsoKnownAs[0]
        }
        binding.knownForActorDetail.text = actor.knownForDepartment
        binding.popularityActorDetail.text = actor.popularity.toString()
        binding.biographyActorDetail.text = actor.biography
        makeTextViewResizable(biography, 15, "View More", true);
        binding.birthdayActorDetail.text = actor.birthday
        if (actor.gender == 2){
            binding.genderActorDetail.text = "Male"
        } else if (actor.gender == 1){
            binding.genderActorDetail.text = "Female"
        }
        binding.placeOfBirthActorDetail.text = actor.placeOfBirth
        presenter!!.loadActorCast(actor.id.toString())
    }

    override fun sendActorCast(actorCast: ActorCast) {
        println(actorCast)
        val castClickListener: AdapterMovies.OnCastClickListener =
            object : AdapterMovies.OnCastClickListener {
                override fun onCastClick(
                    cast: Cast?,
                    position: Int
                ) {
                    fragmentSendMovieDataListener.onSendMovieData(cast!!.id.toString())
                }

            }
        adapterMovie = AdapterMovies(actorCast.cast,castClickListener)
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.knownForListActorDetailList.adapter = adapterMovie
        binding.knownForListActorDetailList.layoutManager = layoutManager
        println()

    }

    override fun onFail() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Fail")
            .setMessage("Something went wrong please try again")
            .create()
        alertDialog.show()
    }

    override fun showRefresh() {
        binding.containerActorDetail.visibility = View.GONE
        binding.loadingIndicatorA.visibility = View.VISIBLE
    }

    override fun hideRefresh() {
        binding.containerActorDetail.visibility = View.VISIBLE//View.GONE
        binding.loadingIndicatorA.visibility = View.GONE//View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        presenter?.loadActor(castId)
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