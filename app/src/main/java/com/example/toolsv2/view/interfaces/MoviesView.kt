package com.example.toolsv2.view.interfaces

import com.example.toolsv2.model.Api.Movies

//Se establece la interfaz de nuestro fragmento de películas que generará los métodos necesarios de nuestra Vista
interface MoviesView {
    fun showMovies(movies:MutableList<Movies>) //Permite obtener el listado de datos de las películas
    fun getMovies() //Permite solicitar las películas a mostrar en la vista
}