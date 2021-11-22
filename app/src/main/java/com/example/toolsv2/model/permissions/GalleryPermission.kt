package com.example.toolsv2.model.permissions

//Permite establecer los métodos que permitirán solicitar y revisar los permisos de la galería y la cámara
interface GalleryPermission {
    fun getPermissionGallery() //Revisa el estatus de los permisos de la galería
    fun getPermissionCamera() //Revisa el estatus de los permisos de la Cámara
    fun checkPermissionGallery():Boolean //Devuelve el estatus de los permisos de la galería
    fun requestPermissionGallery() //Solicita los permisos de la galería
    fun checkPermissionCamera():Boolean //Revisa el estatus de los permisos de la cámara
    fun requestPermissionCamera() //Solicita los permisos de la cámara

}