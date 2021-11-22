package com.example.toolsv2.model.Api

import com.example.toolsv2.view.adapters.Images

//Se crea la interfaz necesaria para tener los métodos al cargar las imagenes, lo cual corresponde al Modelo
interface GalleryUpload {
    fun image(images: MutableList<Images>) //Permite recorrer cada imagén recibida
    fun imageUpload(item: Images) //Inicializar Firebase Storage y subir cada una de las imagenes
}