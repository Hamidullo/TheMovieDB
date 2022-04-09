package com.example.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.actor_casts.Cast
import com.squareup.picasso.Picasso


class AdapterMovies (private val casts: List<Cast?>?, private val onClickListener: OnCastClickListener?): RecyclerView.Adapter<AdapterMovies.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMovies.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterMovies.ViewHolder, position: Int) {
        val cast = casts!![position]
        println(cast)

        val name: String = cast!!.title
        val poster: String = cast.posterPath

        holder.namePersonItem.text = name

        val posterUrl = "https://image.tmdb.org/t/p/w500$poster"
        Picasso.get().load(posterUrl).error(R.drawable.error).into(holder.castImageItem)

        holder.itemView.setOnClickListener {
            onClickListener!!.onCastClick(cast,position)
        }
    }

    override fun getItemCount(): Int {
        return casts!!.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val namePersonItem: TextView = itemView.findViewById(R.id.name_movieItem)
        val castImageItem: ImageView = itemView.findViewById(R.id.movie_imageItem)
    }

    interface OnCastClickListener {
        fun onCastClick(cast: Cast?, position: Int)
    }

}