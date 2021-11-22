package com.example.toolsv2.model.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.example.toolsv2.presenter.GalleryPresent

//La siguiente clase permite implementar cada una de las funciones que verificarán y solicitarán los permisos necesarios para este fragmento
class GalleryPermissionImpl(var galleryPresent: GalleryPresent, val activity: FragmentActivity):
    GalleryPermission {

    //Se declaran valores para poder accesar a los permisos de la galería
    private val PERMISION_GALLERY = 1105
    private val PERMISION_CAMERA = 1111

    //Permite verificar el estatus de los permisos de la galería
    override fun getPermissionGallery() {
        if(checkPermissionGallery()){
            galleryPresent.confirmPermissionGallery(true)
        }else{
            requestPermissionGallery()
        }
    }

    //Permite verificar el estatus de los permisos de la cámara
    override fun getPermissionCamera() {
        if(checkPermissionCamera()){
            galleryPresent.confirmPermissionCamera(true)
        }else{
            requestPermissionCamera()
        }
    }

    //Permite verificar si los permisos de la galería ya fueron aceptados
    override fun checkPermissionGallery(): Boolean {
        if(ActivityCompat
                .checkSelfPermission(activity,android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED ){
            return true
        }
        return false
    }

    //Permite solicitar los permisos de galería
    override fun requestPermissionGallery() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISION_GALLERY
        )
    }

    //Permite verificar si los permisos de la cámara ya fueron aceptados
    override fun checkPermissionCamera(): Boolean {
        if(ActivityCompat
                .checkSelfPermission(activity,android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED ){
            return true
        }
        return false
    }

    //Permite solicitar los permisos de la cámara
    override fun requestPermissionCamera() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA),
            PERMISION_CAMERA
        )
    }


}