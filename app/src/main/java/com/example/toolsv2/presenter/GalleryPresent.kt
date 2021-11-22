package com.example.toolsv2.presenter

import com.example.toolsv2.view.adapters.Images

//Permite crear las funciones que utilizará el Presenter de la galería para la recepción o envío de información
interface GalleryPresent {
    fun getImage(images: MutableList<Images>) //Solicitar imagenes
    fun showImage(images: MutableList<Images>) //Envío de las imagenes
    fun getPermissionGallery() //Solicitar permisos de la galería
    fun getPermissionCamera() //Solicitar permisos de la cámara
    fun confirmPermissionCamera(permission: Boolean) //Envía el estatus de los permisos de la cámara
    fun confirmPermissionGallery(permission: Boolean) //Envía el estatus de los permisos de la Galería
    fun showError(errorMessage: String) //Envía un mensaje si fuera necesario
    fun image(images: MutableList<Images>) //Envía la lista de imagenes a subir
}