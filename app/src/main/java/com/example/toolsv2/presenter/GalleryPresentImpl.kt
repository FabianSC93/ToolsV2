package com.example.toolsv2.presenter

import androidx.fragment.app.FragmentActivity
import com.example.toolsv2.model.interactors.GalleryInteractor
import com.example.toolsv2.model.interactors.GalleryInteractorImpl
import com.example.toolsv2.view.adapters.Images
import com.example.toolsv2.view.interfaces.GalleryView

//La siguiente clase permite implementar cada método que usará el Presenter para la obtención o solicitud de información
class GalleryPresentImpl(private val galleryView:GalleryView, activity:FragmentActivity): GalleryPresent{

    //Se inicia un valor de tipo galleryInteractor para poder enviar al Interactor la view y también el fragment necesario para las funciones
    private val galleryInteractor:GalleryInteractor = GalleryInteractorImpl(this,activity)

    //Envía la solicitud de imagenes que será solicitado al Interactor
    override fun getImage(images: MutableList<Images>) {
        galleryInteractor.getImage(images)
    }

    //Recibe las imagenes solicitadas y se las proporciona a la Vista para mostrarlas
    override fun showImage(images: MutableList<Images>) {
        galleryView.showImage(images)
    }

    //Envía la solicitud del estatus de los permisos de la galería al Interactor
    override fun getPermissionGallery() {
        galleryInteractor.getPermissionGallery()
    }

    //Envía la solicitud del estatus de los permisos de la cámara al Interactor
    override fun getPermissionCamera() {
        galleryInteractor.getPermissionCamera()
    }

    //Recibe el estatus de los permisos de la cámara y se los proporciona a la Vista
    override fun confirmPermissionCamera(permission: Boolean) {
        galleryView.confirmPermissionCamera(permission)
    }

    //Recibe el estatus de los permisos de la galería y se los proporciona a la Vista
    override fun confirmPermissionGallery(permission: Boolean) {
        galleryView.confirmPermissionGallery(permission)
    }

    //Le envía un texto en caso de error para que la vista lo pueda mostrar
    override fun showError(errorMessage: String) {
        galleryView.showError(errorMessage)
    }

    //Le proporciona al interactor la lista de imagenes que se deben subir
    override fun image(images: MutableList<Images>) {
        galleryInteractor.image(images)
    }
}