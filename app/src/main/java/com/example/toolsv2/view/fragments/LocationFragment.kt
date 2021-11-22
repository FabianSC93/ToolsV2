package com.example.toolsv2.view.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.toolsv2.R
import com.example.toolsv2.databinding.FragmentLocationBinding
import com.example.toolsv2.location.LocationService
import com.example.toolsv2.presenter.LocationPresenter
import com.example.toolsv2.presenter.LocationPresenterImpl
import com.example.toolsv2.view.interfaces.LocationView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng

//Se genera la clase que tendrá al fragmento de la ubicación, el cual hereda de LocationView, OnMapReadyCallback y LocationListener
class LocationFragment : Fragment(R.layout.fragment_location), LocationView, OnMapReadyCallback {

    //Se inician nuestras variables globales
    private lateinit var mBinding: FragmentLocationBinding //mBinding será usada para vincular la vista con el código
    private lateinit var locationPresenter: LocationPresenter //Permitirá hacer las peticiones al presentador
    private lateinit var mMap: GoogleMap //Permitirá acceder a los métodos de Google Maps

    //Se sobreescibe el método del fragmento, donde se generá la vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Se instancia mBinding que permite unirlo con la vista
        mBinding = FragmentLocationBinding.bind(view)
        //Se instancia el Presenter, recibiendo al fragmento y la actividad que se requerirá para métodos futuros
        locationPresenter = LocationPresenterImpl(this, requireActivity())

        //Permite inicializar una variable de tipo mapFragment, que nos permite inicializar nuestro mapa y a su vez vincularlo con el elemento que lo contendrá
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Se coloca un listener en nuestro botón creado, el cual al hacer presionado permite verificar los permisos y a su vez obtener la ubicación
        mBinding.btnLocation.setOnClickListener {
            getPermission()
        }
    }

    //Se verifica el estatus en el que se encuentra el fragmento del mapa
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        try {
            mMap = googleMap
            mMap!!.isMyLocationEnabled = true;
        }catch (e: Exception){

        }
    }

    //Permite hacer al presenter la solicitud de la ubicación del usuario
    override fun getLocation() {
        locationPresenter.getLocation()
    }

    //Permite mostrar la ubicación del usuario, dirigiendolo en el mapa y recibiendo como parametro las coordenadas mediante location.
    override fun showLocation(location:Location) {
        val cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),18F)
        mMap!!.moveCamera(cameraUpdate)
        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL //Se establece el tipo de mapa
    }

    //Permite conseguir el estatus de los permisos de ubicación para verificar que sea posible accesar a la ubicación del usuario, solicitandose al usuario
    override fun getPermission() {
        locationPresenter.getPermission()
    }

    //Obtiene el estatus de los permisos mediante un true o un false, dependiendo de la elección del usuario
    override fun statePermission(permission: Boolean) {
        if(permission){
            getLocation()
            ContextCompat.startForegroundService(requireActivity(),
                Intent(requireActivity(), LocationService::class.java))
        }
    }

    //Se muestrá el error un Toast, admeás de recibir un texto como parametro
    override fun showError(errorMessage: String) {
        Toast.makeText(this.context,errorMessage, Toast.LENGTH_LONG).show()
    }
}