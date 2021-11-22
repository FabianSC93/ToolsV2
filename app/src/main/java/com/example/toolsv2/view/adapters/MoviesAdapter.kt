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

//Se generá el adaptador que permitirá inflar cada película en el nuestro item de Movies y a su vez acomodar cada parametro en los elementos de la vista
class MoviesAdapter(val movies:List<Movies>): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    //Se genera el ViewHolder que necesita el adapter, relacionando los elementos de la vista con la data class de Movies que nos marca los parametros
    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvVoteAverage: TextView = itemView.findViewById(R.id.tvVoteAverage)
    }

    //El siguiente método infla la vista en el item de movies y retorna una vista de tipo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MoviesViewHolder(view)
    }

    //El siguiente método obtendrá cada elemento del ViewHolder, además de asignarle una osición dentro de la lista
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        if(movies.isNotEmpty()) { //Se comprueba que la lista no este vacía para comentar con el llenado de cada dato
            val tvTitle:String = movies[position].title
            val tvDate:String = movies[position].date
            val tvVoteAverage:String = movies[position].voteAverage
            val ivPoster:String = movies[position].poster
            Picasso.get().load(ivPoster).into(holder.ivPoster) //Picasso es utilizado para transformar nuestra la URL obtenida a una imagén
            holder.tvTitle.text = tvTitle
            holder.tvDate.text = tvDate
            holder.tvVoteAverage.text = tvVoteAverage
        }
    }

    //El siguiente método solamente brinda de que tamaño será nuestra lista de datos para despúes recorrer el arreglo
    override fun getItemCount(): Int = movies.size

}