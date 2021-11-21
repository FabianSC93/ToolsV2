package com.example.toolsv2.model.Room

import android.app.Application
import androidx.room.Room

//La siguiente clase permitirá construir o realizar nuestra base de datos local y atrbuirle un nombre
class MoviesApp:Application() {
    companion object{
        lateinit var database: MoviesDB   //Se crea una variable de tipo MoviesDB, esta última contendrá los métodos de DAO heredados de una clase Abstracta
    }

    //Mediante la siguiente función se crea la base de datos, obteniendo el nombre de la base de datos y la estructurá que tendrá nuestra tabla.
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            MoviesDB::class.java,
            "MoviesDataBase")
            .build()
    }
}