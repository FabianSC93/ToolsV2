package com.example.toolsv2.view.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.toolsv2.R
import com.example.toolsv2.databinding.FragmentGalleryBinding
import com.example.toolsv2.presenter.GalleryPresent
import com.example.toolsv2.presenter.GalleryPresentImpl
import com.example.toolsv2.view.adapters.GalleryAdapter
import com.example.toolsv2.view.adapters.Images
import com.example.toolsv2.view.interfaces.GalleryView
import java.io.ByteArrayOutputStream

//La siguiente clase almacenará el fragmento de la galería
class GalleryFragment : Fragment(R.layout.fragment_gallery), GalleryView {

    //Se inician las variables globales
    private lateinit var mBinding: FragmentGalleryBinding //Permite vincular la vista con el código
    private lateinit var galleryPresent: GalleryPresent //Permite inicar el Presenter para el envío de solicitud de datos y a su vez la recepción de datos
    private lateinit var galleryAdapter: GalleryAdapter //Permite inicial el Adaptar que nos ayudará para acomodar nuestras imagenes en un Grid
    private lateinit var urls: MutableList<Images> //Permitirá obtener las URL de cada imagen seleccionada

    private var PICK_IMAGE_MULTIPLE = 1 //Para obtener una respuesta de la galería
    private val cameraRequest = 1888 //Para obtener una respuesta de la cámara

    //Se sobreescribe el método del Fragmento, en donde se generá la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentGalleryBinding.bind(view)  //Se inicializa mBinding uniendolo con la vista
        galleryPresent = GalleryPresentImpl(this, requireActivity())  //Se inicializa el presente, enviandole la vista actual y un activity


        urls = mutableListOf() //Se inicializa como una mutableList vacía para almacenar los datos
        galleryAdapter = GalleryAdapter(this.context,urls) //Se inicializaliza el adapatador, pasandole el contexto y la lista vacía
        mBinding.gvImages.adapter = galleryAdapter //Se vincula el elemento de nuestra vista con el adapter para inflar la vista


        //Se tienen tres botones, una para la cámara, galería y subir, asignandole a cada uno un listener para ingresar a los permisos inicialmente y posterior a cada uno de los métodos
        mBinding.apply {
            btnGallery.setOnClickListener {
                getPermissionGallery()
            }
            btnCamera.setOnClickListener {
                getPermissionCamera()
            }
            btnUpload.setOnClickListener {
                image(urls)
            }
        }
    }

    //Esta función permite obtener la respuesta que se obtiene al cargar las imagenes en nuestra vista
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {

            if (requestCode === PICK_IMAGE_MULTIPLE && resultCode === Activity.RESULT_OK && null != android.R.attr.data) {
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                if (data!!.getClipData() != null) {
                    val count = data!!.clipData!!.itemCount
                    if (count>0) {
                        for (i in 0 until count) {
                            urls.add(Images(data.clipData!!.getItemAt(i).uri))
                        }
                    }
                } else{
                    val imageUri = data?.data
                    urls.add(Images(imageUri))
                    getImage(urls)
                }
                getImage(urls)
            } else if (requestCode == cameraRequest) {
                val photo: Bitmap = data?.extras?.get("data") as Bitmap
                urls.add(Images(getImageUri(photo)))
                getImage(urls)
            }else {
                Toast.makeText(
                    this.context, "No has tomado una imagén",  //En caso de estar vacío, aparecerá el siguiente mensaje
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this.context, "Hubo un problema", Toast.LENGTH_LONG) //En caso de tener algún problema se notificará al usuario
                .show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    //La siguiente función transforma la imagen a un Bitmap y realiza un almacenamiento de forma local pero temporal
    private fun getImageUri(bitmap: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val rnds = (1000..100000).random()
        val path = MediaStore.Images.Media.insertImage(
            this.context?.contentResolver,
            bitmap,
            rnds.toString(),
            null
        )
        return Uri.parse(path)
    }

    //La siguiente función permite abrir la cámara por un intent
    private fun selectPhotoCamara() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, cameraRequest)
    }

    //La siguiente función permite abrir la galería mediante un intent y obtener el contenido
    private fun selectPhotoGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }

    //La siguiente función permite hacer la solicitud al Presenter de las imagenes que se van a subir
    override fun getImage(images: MutableList<Images>) {
        galleryPresent.getImage(images)
    }

    //La siguiente función permite mostrar las imagenes obtenidas mediante el Presenter, comprobando antes si no esta vacío el arreglo
    //En caso de no estar vacío se le notifica al adaptador
    override fun showImage(images: MutableList<Images>) {
        urls = if(images.size>0) {
            images
        }else mutableListOf()
        galleryAdapter.notifyDataSetChanged()
    }

    //La siguiente función permite solicitar los permisos de acceso a la galería al Presenter
    override fun getPermissionGallery() {
        galleryPresent.getPermissionGallery()
    }

    //La siguiente función permite solicitar los permisos de acceso a la Camara al Presenter
    override fun getPermissionCamera() {
        galleryPresent.getPermissionCamera()
    }

    //La siguiente función permite obtener el estatus de los permisos de la camara que proporcionó el Presenter
    override fun confirmPermissionCamera(permission: Boolean) {
        if(permission){
            selectPhotoCamara()
        }
    }

    //La siguiente función permite obtener el estatus de los permisos de la galería que proporcionó el Presenter
    override fun confirmPermissionGallery(permission: Boolean) {
        if(permission){
            selectPhotoGallery()
        }
    }

    //La siguiente función permite obtener un mensaje que puede mostrarse en caso de algún error
    override fun showError(errorMessage: String) {
        Toast.makeText(this.context, errorMessage, Toast.LENGTH_LONG).show()
    }

    //La siguiente función permite obtener cada imagen y generar una lista de las mismas
    override fun image(images: MutableList<Images>) {
        galleryPresent.image(images)
    }
}