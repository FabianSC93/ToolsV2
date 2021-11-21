package com.example.toolsv2.model.interfaces
//La siguiente interfaz establece los métodos que necesitará el Interactor para hacer la consulta a los repositorios
interface MoviesInteractor {

    //La siguiente función permitirá realizar el consumo de la Rest API
    fun getApi()
}