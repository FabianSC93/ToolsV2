package com.example.toolsv2.view.interfaces


import android.location.Location

//La siguiente interfaz permite crear los métodos necesarios para nuestra vista, en este del fragmento de la ubicación
interface LocationView {
    fun getLocation() //Permite solicitar la ubicación
    fun showLocation(location: Location) //Permite obtener la ubicación para despues mostrarla
    fun getPermission() //Permite solicitar el estatus de los permisos
    fun statePermission(permission: Boolean) //Permite obtener el estatus de los permisos
    fun showError(errorMessage: String) //Permite obtener el mensaje que mostrará en caso de un error con la localización
}