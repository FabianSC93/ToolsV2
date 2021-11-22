package com.example.toolsv2.model.permissions

//La siguiente interface se establecen los métodos necesarios para obtener y verificar el estatus de los permisos
interface LocationPermission {
    //Solicitará los permisos
    fun requestPermission()
    //Verificará el estatus de los permisos y si no mediante funciones los solicitará
    fun getPermission()
    //Se comprueba cada uno de los permisos si están autorizados
    fun checkPermission():Boolean
    //Verifica si la ubicación esta activa
    fun isLocationEnable():Boolean
}