package com.example.toolsv2.model.repositories

import com.example.toolsv2.view.adapters.Images

//Permite plantear las funciones que debe tener el repositorio para enviar las imagenes al Presenter
interface GalleryRepository {
    fun getImage(images: MutableList<Images>)
}