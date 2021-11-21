package com.example.toolsv2.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toolsv2.R
import com.example.toolsv2.model.Api.Movies
import com.squareup.picasso.Picasso

class MoviesAdapter(val movies:List<Movies>): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvVoteAverage: TextView = itemView.findViewById(R.id.tvVoteAverage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MoviesViewHolder(view)

//       val layoutInflater = LayoutInflater.from(parent.context)
//        return MoviesViewHolder(layoutInflater.inflate(R.layout.item_movies,parent,false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        if(movies.isNotEmpty()) {


            val tvTitle:String = movies[position].title
            val tvDate:String = movies[position].date
            val tvVoteAverage:String = movies[position].voteAverage
            val ivPoster:String = movies[position].poster
            Picasso.get().load(ivPoster).into(holder.ivPoster)
            holder.tvTitle.text = tvTitle
            holder.tvDate.text = tvDate
            holder.tvVoteAverage.text = tvVoteAverage
        }
    }

    override fun getItemCount(): Int = movies.size


/*    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val image= movies[position].poster_path
        val title= movies[position].title
        val date = movies[position].release_date
        val vote = movies[position].vote_average
        holder.bind(image,title,date,vote)
    }

    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }*/
}