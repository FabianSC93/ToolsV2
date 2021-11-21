package com.example.toolsv2.model.Api

import com.example.toolsv2.model.Room.MoviesApp
import com.example.toolsv2.model.Room.MoviesTable
import com.example.toolsv2.model.interfaces.MoviesRepositoryApi
import com.example.toolsv2.present.MoviesPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

//Esta clase nos permitirá hacer uso de las funciones establecidas en la interfaz de MoviesRepositoryApi
class MoviesRepositoryApiImpl(var moviesPresenter:MoviesPresenter): MoviesRepositoryApi {

    //Se realizar el consumo de la API, la cual se establece en una corrutina para no saturar el hilo principal
    override fun getApi() {

        //La siguiente línea de código establece la corrutina
        CoroutineScope(Dispatchers.IO).launch {

            /*Con esto se hace uso de la inicialización de Retrofit, se obtiene la URL faltante con la ayuda de la clase MoviesApi,
            en este caso se obtendrán la películas "top_rated"*/
            val call = initRetrofit().create(MoviesApi::class.java)
                .getTopRatedMovies("top_rated?api_key=3bef72225f8cdb4be3217225b8e30d80")

            //El resultado obteniedo se contruye de tipo JsonDataTopRated y se almacena en results
            val results = call.body() as JsonDataTopRated

            //Se crea una Lista mutable en dónde posteriormente se almacenará cada dato obtenido en results
            val listMovies:MutableList<Movies> = mutableListOf()

            //Mediante un ciclo For se va almacenado cada dato para cada película
            for (item in results.results){
                val date = Movies("https://image.tmdb.org/t/p/w500${item.poster_path}",item.title,item.release_date,item.vote_average)
                val moviesTable = MoviesTable(Random.nextLong() ,item.title,item.release_date,item.vote_average, "https://image.tmdb.org/t/p/w500${item.poster_path}")
                listMovies.add(date)
                MoviesApp.database.MovieDao().insert(moviesTable)
            }
            /*El resultado obtenido se manda como parametro dentro del método ShowMovies del Presenter,
            permitiendo con esto enviar los datos que realizarán los cambios en nuestra vista, parte del funcionamiento del modelo MVP*/
            moviesPresenter.showMovies(listMovies)
        }
    }

    //El siguiente método heredado nos permite inicialzar Retrofit el cual recibe la URL base de nuestra API
    override fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/") //Esta será la URL base de nuestra API
            .addConverterFactory(GsonConverterFactory.create())  //Aplicamos la conversión al formato Json
            .build()  //Construimos lo que declaramos anteriormente
    }

    //El siguiente método obtendrá los valores almacenados en el dispositivo, mediante el método getAll de la clase DAO
    override fun getDataBase() {

        //Debido a que la función a utilizar esta como suspend fun, es necesario establecerla en una corrutina
        CoroutineScope(Dispatchers.IO).launch {

            val dbGetAll = MoviesApp.database.MovieDao().getAll()   //Se llama la función para obtener los datos de la base de datos local

            val listMovies:MutableList<Movies> = mutableListOf()  //Utilizaremos esta variable para almacenar los datos obtenidos

            //Mediante un ciclo for se recorre cada uno de los elementos y se almacena en la variable anterior
            for (item in dbGetAll){
                val date = Movies(item.image,item.title,item.release_date,item.vote_average)
                listMovies.add(date)
            }
            //Se manada el parametro al Presenter, el cual a su vez lo informará a la vista
            moviesPresenter.showMovies(listMovies)
        }
    }


}