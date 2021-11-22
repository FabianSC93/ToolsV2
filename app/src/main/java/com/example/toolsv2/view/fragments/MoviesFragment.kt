package com.example.toolsv2.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toolsv2.*
import com.example.toolsv2.databinding.FragmentMoviesBinding
import com.example.toolsv2.model.Api.Movies
import com.example.toolsv2.model.resources.CheckNetwork
import com.example.toolsv2.presenter.MoviesPresentImpl
import com.example.toolsv2.presenter.MoviesPresenter
import com.example.toolsv2.view.adapters.MoviesAdapter
import com.example.toolsv2.view.interfaces.MoviesView

//La siguiente clase nos permitirá generar un fragmento que contendrá a las películas consultadas
class MoviesFragment : Fragment(R.layout.fragment_movies), MoviesView {

    //Se inician las variables globales
    private lateinit var mBinding:FragmentMoviesBinding  // mBinding que nos permitirá vincular la vista con el código del fragmento

    private var moviesPresenter: MoviesPresenter? = null  // MoviesPresenter para para poder enviar las peticiones de datos

    //Se sobrescribe uno de los métodos del fragmento, en este caso el que genera la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentMoviesBinding.bind(view) //Se genera la instancia de mBinding para poder inflar la vista

        //Se genera la instancia del Presenter al cual se le pasará la vista y además si hay conexión a internet o no
        moviesPresenter = MoviesPresentImpl(this, CheckNetwork(requireActivity()).isOnline())

        //Se inicia la petición de datos a mostrar, en este caso de las películas
        getMovies()
    }

    //Se sobreescribe la función heredada, en este caso recibe las lista por parte del Presenter
    override fun showMovies(movies: MutableList<Movies>) {
        activity?.runOnUiThread { //Se maneja dentro de un hilo para no saturar el hilo principal, además de que la consulta se maneja en una corrutina

            mBinding.rvMovies.layoutManager = LinearLayoutManager(context) //Se establece el recyclerview de tipo líneal para mostrar cada película
            mBinding.rvMovies.adapter = MoviesAdapter(movies) //Se infla el recyclerview con el adapter, además de obtener cada parametro y acomodarlo en la vista

        }

    }

    //Se sobreescribe y permite hacer la solicitud de información al Presenter, solicitandole las películas
    override fun getMovies() {

        moviesPresenter?.getMovies() //Se establece que puede recibir un nulo para evitar que nuestra aplicación se detenga

    }
}