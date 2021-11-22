package com.example.toolsv2.presenter

import com.example.toolsv2.model.Api.Movies
import com.example.toolsv2.model.interactors.MoviesInteractor
import com.example.toolsv2.model.interactors.MoviesInteractorImpl
import com.example.toolsv2.view.interfaces.MoviesView

//Se crea la clase necesaria para implementar los métodos del presenter de las películas, en este caso recibe a la vista, además de un parametro para revisar el estatus del internet
class MoviesPresentImpl(var moviesView:MoviesView, var checkNetwork: Boolean):MoviesPresenter {
    //Se inicia e instancia una variable de tipo Interactor para enviar los parametros y hacer la respectiva solicitud de información
    val moviesInteractor: MoviesInteractor = MoviesInteractorImpl(this, checkNetwork)

    //El siguiente método permite recibir la información obtenida de la consulta mediante un MutableList y esta información es enviada a la vista para mostrarla al usuario
    override fun showMovies(movies: MutableList<Movies>) {
        moviesView.showMovies(movies)
    }

    //El siguiente método permite hacer la solicitud de las películas al Interactor
    override fun getMovies() {
        moviesInteractor.getApi()
    }
}