package com.example.toolsv2.model.interfaces

import retrofit2.Retrofit
//La siguiente interfaz establecerá los métodos para hacer el consumo de la API y también para inicializar Retrofit que es el utilizado para hacer el consumo
interface MoviesRepositoryApi {

    fun getApi() //Método para realizar el consumo de la API

    fun initRetrofit(): Retrofit //Método para inicializar Retrofit

    fun getDataBase()
}