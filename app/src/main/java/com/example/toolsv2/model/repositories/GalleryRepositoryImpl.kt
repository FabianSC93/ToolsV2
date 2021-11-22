package com.example.toolsv2.model.repositories


import com.example.toolsv2.presenter.GalleryPresent
import com.example.toolsv2.view.adapters.Images

//La siguiente clase permite implementar los métodos necesario para hacer el envío de las imagenes seleccionadas al Presenter quien se lo enviará a la View
class GalleryRepositoryImpl(var galleryPresent: GalleryPresent): GalleryRepository {
    override fun getImage(images: MutableList<Images>) {
        galleryPresent.showImage(images)
    }
}