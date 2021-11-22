package com.example.toolsv2.model.interactors

import androidx.fragment.app.FragmentActivity
import com.example.toolsv2.model.Api.GalleryUpload
import com.example.toolsv2.model.Api.GalleryUploadImpl
import com.example.toolsv2.model.permissions.GalleryPermission
import com.example.toolsv2.model.permissions.GalleryPermissionImpl
import com.example.toolsv2.model.repositories.GalleryRepository
import com.example.toolsv2.model.repositories.GalleryRepositoryImpl
import com.example.toolsv2.presenter.GalleryPresent
import com.example.toolsv2.view.adapters.Images
import javax.inject.Singleton

//Esta clase permite implementar las funciones heredadas y necesarios para Interactor
class GalleryInteractorImpl( galleryPresent: GalleryPresent, activity: FragmentActivity):GalleryInteractor {

    //Se inician las variables globales necesarias para los métodos
    @Singleton
    private val galleryRepository: GalleryRepository = GalleryRepositoryImpl(galleryPresent)
    @Singleton
    private val galleryPermission: GalleryPermission = GalleryPermissionImpl(galleryPresent, activity)
    @Singleton
    private val imageUpload: GalleryUpload = GalleryUploadImpl(galleryPresent )

    //Se solicita al repositorio conseguir las imagenes que se subirán
    override fun getImage(images: MutableList<Images>) {
        galleryRepository.getImage(images)
    }

    //Se solicita al repositorio conseguir los permisos de la galería
    override fun getPermissionGallery() {
        galleryPermission.getPermissionGallery()
    }

    //Se solicita al repositorio conseguir los permisos de la cámara
    override fun getPermissionCamera() {
        galleryPermission.getPermissionCamera()
    }

    //Se solicita al repositorio subir las imagenes seleccionadas por el usuario
    override fun image(images: MutableList<Images>) {
        imageUpload.image(images)
    }
}