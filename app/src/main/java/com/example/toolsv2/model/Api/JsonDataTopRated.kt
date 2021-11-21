package com.example.toolsv2.model.Api

//Se establece una data class que contiene cada uno de los atributos iniciales de nuestra API, no se utilizarán todos los datos pero se obtendrán para una posible mejora.
data class JsonDataTopRated(
    val page:Int,
    val results:List<JsonDataResults>,
    val total_pages:Int,
    val total_results:Int)
