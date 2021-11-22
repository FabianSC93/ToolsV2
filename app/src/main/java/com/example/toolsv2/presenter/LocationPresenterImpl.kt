package com.example.toolsv2.presenter

import android.location.Location
import androidx.fragment.app.FragmentActivity
import com.example.toolsv2.model.interactors.LocationInteractor
import com.example.toolsv2.model.interactors.LocationInteractorImpl
import com.example.toolsv2.view.interfaces.LocationView

//La siguiente clase permite permitirá implementar los métodos necesarios para el presenter, recibiendo la vista y también la el fragmentactivity para métodos posteriores
class LocationPresenterImpl(var locationView: LocationView, activity: FragmentActivity):LocationPresenter {
    //Se instancia el interactor para poder enviar la activity y la vista para la implementación de las solicitud de información
    private val locationInteractor: LocationInteractor = LocationInteractorImpl(this, activity)

    //El siguiente método permite solicitarle al interactor la localización del usuario
    override fun getLocation() {
        locationInteractor.getLocation()
    }

    //El siguiente método permite obtener la localización y enviarsela a la Vista, en este caso al fragmento para mostrarla en el mapa
    override fun showLocation(location: Location) {
        locationView.showLocation(location)
    }

    //El siguiente método permite solicitarle los permisos al interactor para posteriormente ser solicitados al usuario
    override fun getPermission() {
        locationInteractor.getPermission()
    }

    //El siguiente método permite recibir el estatus de los permisos y proporcionarselos a la vista para ver si muestra o no la ubicación
    override fun statePermission(permission: Boolean) {
        locationView.statePermission(permission)
    }

    //El siguiente método permite enviar un mensaje de error a la vista, para que la vista lo muestre mediante un Toast
    override fun showError(errorMessage: String) {
        locationView.showError(errorMessage)
    }
}