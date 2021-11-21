package com.example.toolsv2.model.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Con esta interfaz será posible implementar cada uno de los métodos esenciales en las bases de datos, SELECT, INSERT y UPDATE
@Dao
interface MoviesDao {
    //Su aplicación sera la obtención de datos de la base de datos
    @Query("SELECT * FROM MoviesTable")
    suspend fun getAll(): MutableList<MoviesTable>

    //Su función será colocar nuevos datos en la base de datos
    @Insert
    suspend fun insert(movieEntity: MoviesTable): Long

    //Su función será actualizar datos ya existentes en nuestra base de datos
    @Update
    suspend fun add(movieEntity: MoviesTable): Int
}