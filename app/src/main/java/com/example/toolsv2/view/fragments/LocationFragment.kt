package com.example.toolsv2.view.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.toolsv2.R
import com.example.toolsv2.databinding.FragmentLocationBinding
import com.example.toolsv2.location.LocationService
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class LocationFragment : Fragment(R.layout.fragment_location), OnMapReadyCallback, LocationListener {

    private lateinit var mBinding: FragmentLocationBinding

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var PERMISION_MAP = 1101

    private lateinit var mMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentLocationBinding.bind(view)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        mBinding.btnLocation.setOnClickListener {
            getLastLocation()
            // requestPermissionsLocation()

        }
 /*       if (!checkPermission()) {
            requestPermission()
        }*/

    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(){
        if(checkPermision()){
            if (isLocationEnable()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener{ task->
                    var location: Location? = task.result
                    if (location == null){
                        getNetworkLocation()
                    }else{
                        //requireActivity().startService(,Intent() tent(this, LocationService::class.java))

                        ContextCompat.startForegroundService(requireActivity(),
                            Intent(requireActivity(), LocationService::class.java)
                        )
                        //binding.tvUbicacion.text = "Lat: ${location.latitude} log ${location.longitude} \n Ciudad: " + getCityName(location.latitude, location.longitude) + ", pais"+ getcountryName(location.latitude,location.longitude)
                        var cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),18F)
                        mMap!!.moveCamera(cameraUpdate)
                        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
                    }
                }

            }else{
                Toast.makeText(this.context,"Activa tu servicio de Ubicacion por Favor", Toast.LENGTH_LONG).show()
            }

        }else{
            requestPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getNetworkLocation(){
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()

        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation: Location = p0.lastLocation
            //binding.tvUbicacion.text = "Lat: ${lastLocation.latitude} log ${lastLocation.longitude} \n Ciudad: " + getCityName(lastLocation.latitude, lastLocation.longitude) + ", pais"+ getcountryName(lastLocation.latitude,lastLocation.longitude)
            //mMap.mapType(GoogleMap.MAP_TYPE_NORMAL)

            ContextCompat.startForegroundService(requireActivity(),
                Intent(requireActivity(), LocationService::class.java))
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if(checkPermision()) {
            mMap!!.isMyLocationEnabled = true;
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = MapFragment()
    }


    private fun checkPermision():Boolean{
        if(ActivityCompat
                .checkSelfPermission(requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            return true

        }
        return false
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISION_MAP
        )
    }

    private fun isLocationEnable():Boolean{
        val locationManager = this.context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    private fun getCityName(lat:Double,long: Double):String{
        var cityName = ""
        var geoCoder = Geocoder(this.context, Locale.getDefault())
        var adress = geoCoder.getFromLocation(lat,long,1)
        cityName = adress.get(0).locality
        return cityName

    }

    private fun getcountryName(lat:Double,long: Double):String{
        var countryName = ""
        var geoCoder = Geocoder(this.context, Locale.getDefault())
        var adress = geoCoder.getFromLocation(lat,long,1)
        countryName = adress.get(0).countryName
        return countryName

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onLocationChanged(location: Location) {
        Log.d("TAG", String.format("Nueva ubicación: (%s, %s)",location?.getLatitude(), location?.getLongitude()));

    }
}