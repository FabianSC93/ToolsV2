package com.example.toolsv2.model.Room

import androidx.room.Database
import androidx.room.RoomDatabase

//Inicializa nuestra base de datos, dandole un hombre y la versión
@Database(
    entities = [MoviesTable::class],
    version = 1
)
//Se obtnienen las funcionalidades de DAO para ser implementar en la clase MoviesDao
abstract class MoviesDB:RoomDatabase() {
    abstract fun MovieDao(): MoviesDao
}