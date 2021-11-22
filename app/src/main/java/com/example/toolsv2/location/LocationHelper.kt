package com.example.toolsv2.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.location.Location

class LocationHelper {

    var LOCATION_REFRESH_TIME = 500000 // Se ejecutará el servicio cada 5 minutos
    var LOCATION_REFRESH_DISTANCE = 0 // Se establece la cantidad mínima de metros para hacer una actualizaicón en la posición

    @SuppressLint("MissingPermission")
    fun startListeningUserLocation(context: Context, myListener: MyLocationListener) {
        val mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                myListener.onLocationChanged(location) // calling listener to inform that updated location is available
            }

            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        }

        mLocationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            LOCATION_REFRESH_TIME.toLong(),
            LOCATION_REFRESH_DISTANCE.toFloat(),
            locationListener
        )
    }
}


interface MyLocationListener {
    fun onLocationChanged(location: Location?)
}