package com.example.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.model.moviecast.Cast
import com.squareup.picasso.Picasso


class AdapterCast (private val casts: List<Cast?>?,private val onClickListener: OnCastClickListener?): RecyclerView.Adapter<AdapterCast.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCast.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterCast.ViewHolder, position: Int) {
        val cast = casts!![position]
        println(cast)

        val name: String = cast!!.name
        val character: String = cast.character
        val poster: String = cast.profilePath

        holder.namePersonItem.text = name
        holder.characterPersonItem.text = character

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
        val namePersonItem: TextView = itemView.findViewById(R.id.name_personItem)
        val characterPersonItem: TextView = itemView.findViewById(R.id.character_personItem)
        val castImageItem: ImageView = itemView.findViewById(R.id.cast_imageItem)
    }

    interface OnCastClickListener {
        fun onCastClick(cast: Cast?, position: Int)
    }

}