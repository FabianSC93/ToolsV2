package com.example.toolsv2.model.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

//Se establece la estructura que tendrá la tabla de nuestra base de datos y el nombre que tendrá
@Entity(tableName = "MoviesTable")

//Se establece cada uno de sus atributos o campos que tendrá la tabla
data class MoviesTable(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String,
    var release_date: String,
    var vote_average: String,
    var image: String)
