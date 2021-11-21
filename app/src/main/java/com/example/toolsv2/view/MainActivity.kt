package com.example.toolsv2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.toolsv2.R
import com.example.toolsv2.databinding.ActivityMainBinding
import com.example.toolsv2.view.fragments.GalleryFragment
import com.example.toolsv2.view.fragments.LocationFragment
import com.example.toolsv2.view.fragments.MoviesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //Variable que nos permitirá inicar con el enlace entre el código y las vistas

    private lateinit var mActivityFragment: Fragment
    private lateinit var mFragmentManager: FragmentManager //Declaramos una variables que nos permitirá acceder a los métodos para agregar, quitar o reemplazar los fragmentos

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNav() //Llamos a la función para comenzar con el manejo de los fragmentos
    }

    //Esta función vinculara cada fragmento con una opción del menú creado
    private fun setupBottomNav(){
        mFragmentManager = supportFragmentManager

        //Instanciamos cada uno de los fragmentos
        val moviesFragment = MoviesFragment()
        val locationFragment = LocationFragment()
        val galleryFragment = GalleryFragment()

        mActivityFragment = moviesFragment //Nuestra aplicación iniciara con este fragmento, el de películas

        /*Se inician con las operaciones de cada fragmento, en este caso mantenerlos ocultos, excepto el de MoviesFragment ya que este se verá al inicio
        de la aplicación*/
        mFragmentManager.beginTransaction().add(R.id.hostFragment, moviesFragment , MoviesFragment::class.java.name).commit()
        mFragmentManager.beginTransaction().add(R.id.hostFragment, locationFragment, LocationFragment::class.java.name).hide(locationFragment).commit()
        mFragmentManager.beginTransaction().add(R.id.hostFragment, galleryFragment, GalleryFragment::class.java.name).hide(galleryFragment).commit()

        //De acuerdo al id de nuestro menú, se mostrará cada uno de los fragmentos pero ocultaremos el fragmento que este activo.
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_movies -> {
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(moviesFragment).commit()
                    mActivityFragment = moviesFragment
                    true
                }
                R.id.action_location ->{
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(locationFragment).commit()
                    mActivityFragment = locationFragment
                    true
                }
                R.id.action_gallery ->{
                    mFragmentManager.beginTransaction().hide(mActivityFragment).show(galleryFragment).commit()
                    mActivityFragment = galleryFragment
                    true
                    true
                }
                else -> false
            }
        }

    }



}