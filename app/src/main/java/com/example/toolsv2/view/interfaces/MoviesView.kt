package com.example.toolsv2.view.interfaces

import com.example.toolsv2.model.Api.Movies

interface MoviesView {
    fun showMovies(movies:MutableList<Movies>)
    fun getMovies()
}