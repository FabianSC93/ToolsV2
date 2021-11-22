package com.example.toolsv2.model.Api

import com.example.toolsv2.presenter.GalleryPresent
import com.example.toolsv2.view.adapters.Images
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

//La siguiente clase proporciona permitiraá subir las imagenes recibidas por parte del interactor a Storage Firebase
class GalleryUploadImpl(var galleryPresent: GalleryPresent): GalleryUpload {

    //Se inicia cada una de las variables necesarias, la lista de imagenes, el almacenamiento, métodos de Firebase y el nombre dónde se guardaran
    private lateinit var urls: MutableList<Images>
    private var storageReference: StorageReference? = null
    private val database = Firebase.database
    private val ref = database.getReference("user")

    //Permite recorrer el arreglo de las imagenes para pasarle cada uno a Firebase Storage
    override fun image(images: MutableList<Images>) {
        urls = images
        storageReference = FirebaseStorage.getInstance().reference
        for (item in urls){
            imageUpload(item)
        }
    }

    //Se inicializa Firebase Storage, además de realizar la carga de cada imagén en la nuve
    override fun imageUpload(item: Images) {
        val fileUri = item.uri
        val folder: StorageReference =
            FirebaseStorage.getInstance().getReference().child("User")
        val fileName: StorageReference = folder.child("file" + fileUri!!.lastPathSegment)
        fileName.putFile(fileUri).addOnSuccessListener { taskSnapshot ->
            fileName.getDownloadUrl().addOnSuccessListener { uri ->
                urls.remove(item)
                galleryPresent.showImage(urls)
            }
        }
    }
}