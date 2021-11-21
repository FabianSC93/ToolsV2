package com.example.toolsv2.model.Api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

//Se crea una interfaz que administra la URL y verifica el tipo de respuesta que se tiene
interface MoviesApi {

    /*La siguiente función obtiene la URL faltante de la URL principal de la API
    * además de revisar que dato se obtuvo, se establece como suspend debido a que la consulta se realiza en una corrutina*/
    @GET
    suspend fun getTopRatedMovies(@Url url:String): Response<JsonDataTopRated>
}