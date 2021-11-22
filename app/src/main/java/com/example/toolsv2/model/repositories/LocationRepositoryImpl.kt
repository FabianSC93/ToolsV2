package com.example.toolsv2.model.repositories

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import com.example.toolsv2.presenter.LocationPresenter
import com.google.android.gms.location.*

//La siguiente clase permite obtener la ubicación del usuario, para ello recibe al presentar para el envío de una respuesta y el fragmento donde se mostrará
//herando también de LocationRepository sus métodos
class LocationRepositoryImpl(var locationPresenter: LocationPresenter, var activity:FragmentActivity):
    LocationRepository {

    //Se inicializa una variable que permitirá interactuar con el proveedor que genere la localización del usuario
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    //Se inicializa una variable que nos permitirá obtener la ubicación en tiempo real
    private lateinit var locationRequest: LocationRequest

    //La siguiente función obtendrá la ubicación del usuario y se la mandará al presenter para interactuar con la vista
    @SuppressLint("MissingPermission")
    override fun getLocation() {

        //Se instancia la variable creada anteriormente, pasandole el fragmento
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

        //Se comprueba si la ubicación esta vacía en caso de estarlo se solicita y si no se envía al presenter
        fusedLocationProviderClient.lastLocation.addOnCompleteListener{ task->
            val location: Location? = task.result
            if (location == null){
                getNetworkLocation()
            }else{
                locationPresenter.showLocation(location)
                //requireActivity().startService(,Intent() tent(this, LocationService::class.java))

                //ContextCompat.startForegroundService(activity,
                  //  Intent(activity, LocationService::class.java)
                //)
                //binding.tvUbicacion.text = "Lat: ${location.latitude} log ${location.longitude} \n Ciudad: " + getCityName(location.latitude, location.longitude) + ", pais"+ getcountryName(location.latitude,location.longitude)


            }
        }

    }

    //El siguiente método permitirá obtener la ubicación en tiempo, estableciendo unos parametros de actualización
    @SuppressLint("MissingPermission")
    override fun getNetworkLocation() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()

        )
    }

    //La siguiente variable permite obtener mediante una función la última ubicación obtenida
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation: Location = p0.lastLocation
            locationPresenter.showLocation(lastLocation)
            /*ContextCompat.startForegroundService(activity,
                Intent(activity, LocationService::class.java))*/
        }
    }
}