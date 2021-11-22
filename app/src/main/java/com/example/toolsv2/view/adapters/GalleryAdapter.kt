package com.example.toolsv2.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.toolsv2.R

//La siguiente clase permite generar el adaptador de las fotos o imagenes que subiremos en Storage Firebase, recibiendo el contexto y el lista de URL de cada imagén
//Este adaptador nos permitirá inflar nuestro item de cada imagen y también para la asignación a nuestro Grid que es como acomodaremos cada imagen seleccionada
class GalleryAdapter(val context: Context?, val urls: List<Images>) : BaseAdapter() {
    //Se inicia una variable de tipo Layoutinflater
    private var layoutInflater: LayoutInflater

    //Se hace uso de la variable anterior para poder inflar
    init {
        layoutInflater = (context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE)) as LayoutInflater
    }

    //Se obtiene el tamaño de la lista recibida para despúes recorrer cada elemento
    override fun getCount(): Int = urls.size

    //No hace uso de este método debido a que no es necesario obtener algún elemento
    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    //Se obtiene el id de cada imagén
    override fun getItemId(position: Int): Long = position.toLong()


    //Se asigna cada elemento a nuestro item que contendrá cada imagen o foto para mostrarselo al usuario
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =  LayoutInflater.from(parent!!.context).inflate(R.layout.item_image, parent, false) //Se infla el item creado
        val ivImages : ImageView = view.findViewById(R.id.ivImages) //Se obtienen los elementos del item
        ivImages.setImageURI(urls[position].uri) //Se asigna la URL de cada imagen el elemento del item, en este caso a una ImageView
        return view //Se retorna una vista
    }
}