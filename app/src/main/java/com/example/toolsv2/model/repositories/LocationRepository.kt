package com.example.toolsv2.model.repositories

//La siguiente interfaz permitirá crear los métodos que utilizará nuestro Respositorio o Modelo
interface LocationRepository {
    //El siguiente método obtendrá nuestra ubicación
    fun getLocation()
    //El siguiente método permite obtener la ubicación en tiempo real
    fun getNetworkLocation()
}