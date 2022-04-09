package com.example.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.movie.Genre

class AdapterGenres(private val genres: List<Genre?>?): RecyclerView.Adapter<AdapterGenres.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterGenres.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterGenres.ViewHolder, position: Int) {
        val genre = genres!![position]
        println(genre)
        val voteAverage: String = genre!!.name
        holder.genresTextItem.text = voteAverage
    }

    override fun getItemCount(): Int {
        return genres!!.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val genresTextItem: TextView = itemView.findViewById(R.id.genres_textItem)
    }
}