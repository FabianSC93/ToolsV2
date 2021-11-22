package com.example.toolsv2.model.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.example.toolsv2.presenter.LocationPresenter

//Con la siguiente clase se implementan los métodos para verificar los permisos, vinculandolo con el presenter para enviar la respuesta y el fragmento en donde se mostrará
class LocationPermissionImpl(var locationPresenter: LocationPresenter, var activity:FragmentActivity):
    LocationPermission {
    //Se asigna una clave a los permisos para ser usada posteriormente en la verificación
    private var PERMISION_MAP = 1101

    //El siguiente método permite solicitar los permisos al usuario para poder acceder a su ubicación
    override fun requestPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISION_MAP
        )
    }

    //El siguiente método verifica si los permisos ya fueron aceptados y si esta activa la ubicación, en caso de que no los solicita mediante el método anterior
    override fun getPermission() {
        if(checkPermission()){
            if (isLocationEnable()){
                locationPresenter.statePermission(true)  //Si los permisos estan aceptados y la ubicación activa retorna un true al presenter
            }else{
                locationPresenter.showError("Activar servicio de ubicación")  //En caso contrario envía un mensaje de que su ubicación no esta activa
            }

        }else{
            requestPermission()    //En caso de que los permisos no estan aceptados los solicitada
        }
    }

    //La siguiente función permite ver el estatus de los permisos mediante "checkSelfPermission"
    override fun checkPermission(): Boolean {
        if(ActivityCompat
                .checkSelfPermission(activity,Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            return true  //En caso de que ambos permisos esten aceptados se retornará un truecheckPermision

        }
        return false //Caso contrario retornara un false ya que algúno o los dos no fueron aceptados
    }

    //La siguiente función permite verificar el estatus de la ubicación del telefono para saber si esta activa
    override fun isLocationEnable(): Boolean {
        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager //Se inicializa el servicio de localización
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled( //Se realiza la comprobación de la localización por algún de los dos proveedores
            LocationManager.NETWORK_PROVIDER)
    }

}