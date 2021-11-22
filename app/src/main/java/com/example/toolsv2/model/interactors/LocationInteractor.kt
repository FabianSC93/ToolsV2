package com.example.toolsv2.model.interactors

//La siguiente interfaz nos permitirá implementar ciertos métodos que serán utiles para el interactor
interface LocationInteractor {
    //El método que obtendrá nuestrá ubicación
    fun getLocation()

    //El siguiente método obtendrá los permisos que se procesarán en LocalPermissionImpl
    fun getPermission()
}