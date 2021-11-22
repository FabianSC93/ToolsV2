package com.example.toolsv2.presenter

import com.example.toolsv2.model.Api.Movies

//La siguiente Interface permite establecer los métodos que necesitará hacer el presenter para consultar u obtener información
interface MoviesPresenter{
    fun showMovies(movies:MutableList<Movies>) //Para recibir los datos de cada película
    fun getMovies() //Para solicitar la información correspondiente a las películas
}