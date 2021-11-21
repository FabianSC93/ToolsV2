package com.example.toolsv2.model.resources

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

//La siguiente clase nos permitirá consultar el estatus de la conexión a internet, esta necesitara un contexto que se proporciona en el fragment
class CheckNetwork(var context: Context) {

    fun isOnline(): Boolean { //Sera de tipo Boolean ya que dependiendo de la conexión esta podrá ser true o false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager  //Se instancia un valor de tipo ConnectivityManager

        //Se analiza inicialmente si el parametro es diferente de nulo para asegurar que se tiene el valor
        if (connectivityManager != null) {

                //Se comprueba el nivel de SDK del dispositivo en caso de ser mayor o igual a 22, ejecutará la siguiente comprobación
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    //Si conexión es correcta se retornará un true
                    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    if (capabilities != null) {
                        return true
                    }
                    //Se comprueba el nivel de SDK del dispositivo en caso de ser menor a 22, ejecutará la siguiente comprobación
                } else {
                    return try {
                        //Si conexión es nula se retornará un false
                        if (connectivityManager.activeNetworkInfo == null) {
                            false

                            //Si conexión es correcta se retornará un true
                        } else {
                            connectivityManager.activeNetworkInfo?.isConnected!!
                        }
                    } catch (e: Exception) {
                        false
                    }
                }

        }
        return false
    }
}