package com.example.toolsv2.model.interactors

import com.example.toolsv2.model.repositories.MoviesRepositoryApi
import com.example.toolsv2.model.repositories.MoviesRepositoryApiImpl
import com.example.toolsv2.presenter.MoviesPresenter
import javax.inject.Singleton

//Esta clase nos permite hacer uso de nuestra interfaz llama MoviesInteractor, además de poder vincularse con el Presenter y con nuestro repositorio
class MoviesInteractorImpl(moviesPresenter:MoviesPresenter, var checkNetwork: Boolean):MoviesInteractor {

    //El siguiente valor nos permite instanciar el Presenter, lo cual es posible al momento de recibirlo como parámetro
    @Singleton
    val moviesRepositoryApi: MoviesRepositoryApi = MoviesRepositoryApiImpl(moviesPresenter)

    /*El siguiente método es heredado de la interfaz MoviesInteractor y permite vincularse con el repositorio, obteniendo de el el método getApi
    que posteriormente nos ayudará para hacer el consumo de la API o consulta de la base de datos de acuerdo al estatus de la conexión a internet*/
    override fun getApi() {
        if(checkNetwork) {
            moviesRepositoryApi.getApi()   //Consumo de la API
        }else{
            moviesRepositoryApi.getDataBase()  //Consulta en la base de datos local
        }
    }
}