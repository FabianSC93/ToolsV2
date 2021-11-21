package com.example.toolsv2.model.Api

//Se establece una data class que contiene cada uno de los atributos de las películas, no se utilizarán todos los datos pero se obtendrán para una posible mejora.
data class JsonDataResults(
    val adult:Boolean,
    val backdrop_path:String,
    val genre_ids:List<Int>,
    val id:Int,
    val original_language:String,
    val original_title:String,
    val overview:String,
    val popularity:String,
    val poster_path:String,
    val release_date:String,
    val title:String,
    val video:Boolean,
    val vote_average:String,
    val vote_count:String)
