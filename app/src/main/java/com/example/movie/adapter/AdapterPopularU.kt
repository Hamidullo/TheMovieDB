package com.example.movie.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.MainActivity2
import com.example.movie.R
import com.example.movie.model.upcoming.Result
import com.squareup.picasso.Picasso
import me.tankery.lib.circularseekbar.CircularSeekBar

class AdapterPopularU(private val movies: List<Result?>?, private val context: Context): RecyclerView.Adapter<AdapterPopularU.ViewHolder>(){

    /*override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val movie = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        }
        val voteAverageTextMain = convertView!!.findViewById<View>(R.id.vote_average_textMain) as TextView
        val titleTextMain = convertView.findViewById<View>(R.id.title_textMain) as TextView
        val releaseDateMain = convertView.findViewById<View>(R.id.release_dateMain) as TextView
        val posterItemMain = convertView.findViewById<View>(R.id.poster_itemMain) as ImageView
        val circleProgressBarMain = convertView.findViewById<View>(R.id.circle_progress_barMain) as CircularSeekBar

        val voteAverage: String = (movie!!.voteAverage.toFloat()*10).toString()
        val title: String = movie.originalTitle
        val date: String = movie.releaseDate
        val poster: String = movie.posterPath

        circleProgressBarMain.progress = movie.voteAverage.toFloat()*10
        voteAverageTextMain.text = voteAverage
        titleTextMain.text = title
        releaseDateMain.text = date

        val posterUrl = "https://image.tmdb.org/t/p/w500$poster"
        //  https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg

        println(posterUrl)

        Picasso.get().load(posterUrl).error(R.drawable.error).into(posterItemMain)

        return convertView
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPopularU.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterPopularU.ViewHolder, position: Int) {
        val movie = movies!![position]
        println(movie)

        val voteAverage: String = (movie!!.voteAverage*10).toString()
        val title: String = movie.title
        val date: String = movie.releaseDate
        val poster: String = movie.posterPath

        holder.voteAverageTextMain.text = voteAverage
        holder.averageRatingMain.text = voteAverage
        holder.titleTextMain.text = title
        holder.releaseDateMain.text = date
        holder.circleProgressBarMain.progress = movie.voteAverage.toFloat()*10
        holder.circleProgressBarMain.setOnTouchListener { view, motionEvent -> true }
        val posterUrl = "https://image.tmdb.org/t/p/w500$poster"
        Picasso.get().load(posterUrl).error(R.drawable.error).into(holder.posterItemMain)

        holder.itemView.setOnClickListener {
            val intent = Intent(context,MainActivity2::class.java)
            val sharedPreferences: SharedPreferences = context.getSharedPreferences("MovieDBAPP",Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("movieID", movie.id.toString())
            editor.apply()
            editor.commit()
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies!!.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val voteAverageTextMain: TextView = itemView.findViewById(R.id.vote_average_textMain)
        val averageRatingMain: TextView = itemView.findViewById(R.id.average_ratingMain)
        val titleTextMain: TextView = itemView.findViewById(R.id.title_textMain)
        val releaseDateMain: TextView = itemView.findViewById(R.id.release_dateMain)
        val posterItemMain: ImageView = itemView.findViewById(R.id.poster_itemMain)
        val circleProgressBarMain: CircularSeekBar = itemView.findViewById(R.id.circle_progress_barMain)

    }

}