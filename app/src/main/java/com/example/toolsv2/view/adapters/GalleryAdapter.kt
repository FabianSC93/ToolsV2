package com.example.toolsv2.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.toolsv2.R

class GalleryAdapter(val context: Context?, val urls: List<Images>) : BaseAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    init {
        layoutInflater = (context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE)) as LayoutInflater
    }


    override fun getCount(): Int {
        //return img.size
        return urls.size
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =  LayoutInflater.from(parent!!.context).inflate(R.layout.item_image, parent, false)
        val ivImages : ImageView = view.findViewById(R.id.ivImages)
        //var tvGvFotos :TextView= view.findViewById(R.id.tvGvFotos)
        //ivGvFotos.setImageResource(img[position])
        ivImages.setImageURI(urls[position].uri)
        /*tvGvFotos.text = clipData.getItemAt(position).text*/
        return view
    }
}