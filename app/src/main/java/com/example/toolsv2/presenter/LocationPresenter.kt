package com.example.toolsv2.presenter

import android.location.Location

//La siguiente interfaz, establecerá los métodos que ocupará el Presenter, tanto para el envío de información, como de recepción
interface LocationPresenter {
    fun getLocation() //Método que permite solicitar la localización
    fun showLocation(location:Location) //Método que permite obtener la localización para enviarla a la Vista
    fun getPermission() //Método que permite solicitar los permisos
    fun statePermission(permission: Boolean) //Método que permite recibir el estatus de los permisos para notificarle a la vista
    fun showError(errorMessage: String) //En caso de error se envía el mensaje que debe mostrar la vista
}