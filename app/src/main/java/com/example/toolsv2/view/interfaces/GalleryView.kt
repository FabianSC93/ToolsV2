package com.example.toolsv2.view.interfaces

import com.example.toolsv2.view.adapters.Images

//La siguiente interfaz indicará los métodos a implementar en la vista para la solicitud y recepción de datos
interface GalleryView {
    fun getImage(images: MutableList<Images>) //Solicita las imagenes que va a subir al Storage de Firebase
    fun showImage(images: MutableList<Images>) //Muestra las imagenes que recibió
    fun getPermissionGallery() //Solicita los permisos para ingresar a la galería
    fun getPermissionCamera() //Solicita los permisos para ingresar a la camara
    fun confirmPermissionCamera(permission: Boolean) //Recibir el estatu de los permisos de la cámara
    fun confirmPermissionGallery(permission: Boolean) //Recibir el estatus de los permisos de la galería
    fun showError(errorMessage: String) //Permite recibir un mensaje
    fun image(images: MutableList<Images>) //Permite realizar un lista de las URL de las imagenes
}