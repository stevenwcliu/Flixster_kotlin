package com.example.flixster

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//import com.bumptech.glide.annotation.GlideModule
//import com.bumptech.glide.module.AppGlideModule
//
//@GlideModule
//class MyAppGlideModule : AppGlideModule() {
//    // leave empty for now
//}

// glue between views and their recyclerview
const val MOVIE_EXTRA = "MOVIE_EXTRA"

class MovieAdaptor(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    // implement the interface: View.OnClickListener
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        init { // register the click listener
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) { // bind particular element in our data set into a view
            // attach a click listener and get notified when the user taps on an element
            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            // populate imageview
            Glide.with(context).load(movie.posterImageURL).into(ivPoster)
        }

        override fun onClick(p0: View?) {
            //Goals
            // 1. Get notified of the particular movie got which was clicked
            val movie = movies[adapterPosition]
            // Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
            // 2. Use the intent system to navigate to the new screen/activity
            val intent = Intent(context, DetailActivity::class.java)
            // intent.putExtra("movie_title", movie.title) // only can put particular primitive types of data into intent extra
            // to pass complex data in a bundle, use serialization or parcelable(ways to tell the android system how to deconstruct the complicated object into components parts)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}
