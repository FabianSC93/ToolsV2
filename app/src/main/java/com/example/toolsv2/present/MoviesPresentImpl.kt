package com.example.toolsv2.present

import com.example.toolsv2.model.Api.Movies
import com.example.toolsv2.model.interfaces.MoviesInteractor
import com.example.toolsv2.model.Interactors.MoviesInteractorImpl
import com.example.toolsv2.model.resources.CheckNetwork
import com.example.toolsv2.view.interfaces.MoviesView

class MoviesPresentImpl(var moviesView:MoviesView, var checkNetwork: Boolean):MoviesPresenter {
    val moviesInteractor: MoviesInteractor = MoviesInteractorImpl(this, checkNetwork)

    override fun showMovies(movies: MutableList<Movies>) {
        moviesView.showMovies(movies)
    }

    override fun getMovies() {
        moviesInteractor.getApi()
    }
}