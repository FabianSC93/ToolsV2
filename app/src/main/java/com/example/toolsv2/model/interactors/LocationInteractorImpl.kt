package com.example.toolsv2.model.interactors

import androidx.fragment.app.FragmentActivity
import com.example.toolsv2.model.permissions.LocationPermission
import com.example.toolsv2.model.permissions.LocationPermissionImpl
import com.example.toolsv2.model.repositories.LocationRepository
import com.example.toolsv2.model.repositories.LocationRepositoryImpl
import com.example.toolsv2.presenter.LocationPresenter
import javax.inject.Singleton

//Esta clase implementará los métodos de la interfaz LocationInteractor,  el cual recibirá al presentar para vincularse y el fragmento que
//ocuparemos posteriormente para brindar un contexto
class LocationInteractorImpl(locationPresenter: LocationPresenter, activity: FragmentActivity):LocationInteractor {

    //Inicializamos nuestra variable de tipo LocationRespository ya que el interactor es el encargador de llamar a nuestro repositorio
    //recibiendo al Presenter y el Fragment ocuapdo en nuestro respositorio
    @Singleton
    val locationRepository: LocationRepository = LocationRepositoryImpl(locationPresenter,activity)
    @Singleton
    val locationPermission: LocationPermission = LocationPermissionImpl(locationPresenter,activity)

    //Se sobreescribe el método heredado enlazandolo con nuestro repositorio, para hacer el llamado de información
    override fun getLocation() {
        locationRepository.getLocation()
    }

    //Se sobreescribe el método heredado, el cual nos permitirá hacer la solicitud a nuestro "repositorio" para verificar cada uno de los permisos de ubicación
    override fun getPermission() {
        locationPermission.getPermission()
    }
}