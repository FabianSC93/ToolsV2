package com.example.toolsv2.model.interactors

import com.example.toolsv2.view.adapters.Images
//Permite contemplar los métodos necesarios para el Interactor del apartado de la galería
interface GalleryInteractor {
    fun getImage(images: MutableList<Images>) //Solicitar las imagenes al repositorio
    fun getPermissionGallery() //Solicitar los permisos de la galería al repositorio
    fun getPermissionCamera() //Solicitar los permisos de la cámara al repositorio
    fun image(images: MutableList<Images>) //Envía las imagenes a subir a Firestone Storage al repositorio
}