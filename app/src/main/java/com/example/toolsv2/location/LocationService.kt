package com.example.toolsv2.location

import android.app.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.*
import com.example.toolsv2.R
import com.google.firebase.firestore.FirebaseFirestore

class LocationService: Service() {
    private val NOTIFICATION_CHANNEL_ID = "my_notification_location"
    private val TAG = "LocationService"

    override fun onCreate() {
        super.onCreate()
        isServiceStarted = true
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setOngoing(false)
                .setSmallIcon(R.drawable.ic_launcher_background)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_ID, NotificationManager.IMPORTANCE_LOW
            )
            notificationChannel.description = NOTIFICATION_CHANNEL_ID
            notificationChannel.setSound(null, null)
            notificationManager.createNotificationChannel(notificationChannel)
            startForeground(1, builder.build())
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val timer = Timer()
        LocationHelper().startListeningUserLocation(
            this, object : MyLocationListener {
                override fun onLocationChanged(location: Location?) {
                    mLocation = location
                    mLocation?.let {coordinate ->

                            Log.d(TAG,"onLocationChanged: Latitude ${coordinate.latitude} , Longitude ${coordinate.longitude}")
                            val mLocation = Coordinates("${coordinate.latitude}","${coordinate.longitude}")
                            val db = FirebaseFirestore.getInstance()
                            db.collection("Locations").add(mLocation)
                                .addOnSuccessListener {  snapShot->
                                    try {
                                        generateNotification("Ubicaci??n actual",coordinate.latitude.toString(),coordinate.longitude.toString())

                                    }catch (e: Exception){}
                                }
                                .addOnFailureListener {
                                    try {
                                        generateNotification("No se obtuvo ubicaci??n",coordinate.latitude.toString(),coordinate.longitude.toString())

                                    }catch (e: Exception){

                                    }
                                }

                    }
                }
            })
        return START_STICKY
    }

    fun generateNotification(title: String, lat:String, lon:String){
        val channelId = "canal_notificacion"
        val Channelname = "com.example.toolsv2"
        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_location_on)
            .setContentTitle(title)
            .setContentText("lat: ${lat} y lon: ${lon}")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("lat: ${lat} y lon: ${lon}"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId, Channelname, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0,builder.build())

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        isServiceStarted = false

    }

    companion object {
        var mLocation: Location? = null
        var isServiceStarted = false
    }
}