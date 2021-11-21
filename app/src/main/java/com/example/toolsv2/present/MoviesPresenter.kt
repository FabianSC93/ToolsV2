package com.example.toolsv2.present

import com.example.toolsv2.model.Api.Movies

interface MoviesPresenter{
    fun showMovies(movies:MutableList<Movies>)
    fun getMovies()
}