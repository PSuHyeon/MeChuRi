package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.location.*
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons

class Specific_location : AppCompatActivity(), OnMapReadyCallback {
    lateinit var naverMap : NaverMap
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var myLocation : LatLng
    lateinit var placeLocation : LatLng
    var index = 0
    var myLocationMarker = Marker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_location)

        var mapView = findViewById(R.id.mapView) as MapView

        index = intent.getIntExtra("index", place_list.size-1)
        placeLocation = LatLng(place_list.get(index).locN, place_list.get(index).locE)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

    }

    override fun onMapReady(p0: NaverMap) {

        this.naverMap = p0

        var marker = Marker()
        marker.position = placeLocation // 가게 위경도
        marker.map = p0

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setUpdateLocationListener()


    }

    lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    private fun setUpdateLocationListener() {
        val locationRequest = LocationRequest.create()

        locationRequest.run{

            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000

        }

        locationCallback = object : LocationCallback(){

            override fun onLocationResult(p0: LocationResult) {
                p0 ?: return
                for ((i, location) in p0.locations.withIndex()){

                    setLastLocation(location)

                }
            }

        }

        fusedLocationProviderClient.requestLocationUpdates(

            locationRequest,
            locationCallback,
            Looper.myLooper()

        )

    }

    fun setLastLocation(location : Location){

        myLocation = LatLng(location.latitude, location.longitude)
        myLocationMarker.position = myLocation
        Log.d("check", ""+myLocation)
        myLocationMarker.map = naverMap

        var boundMyLocation : LatLng
        var boundPlaceLocation : LatLng

        if(myLocation.latitude >= placeLocation.latitude && myLocation.longitude >= placeLocation.longitude) {

            boundMyLocation = LatLng(myLocation.latitude + 0.005,myLocation.longitude + 0.005)
            boundPlaceLocation = LatLng(placeLocation.latitude - 0.005, placeLocation.longitude - 0.005)

        } else if(myLocation.latitude >= placeLocation.latitude && myLocation.longitude < placeLocation.longitude){

            boundMyLocation = LatLng(myLocation.latitude + 0.005,myLocation.longitude - 0.005)
            boundPlaceLocation = LatLng(placeLocation.latitude - 0.005, placeLocation.longitude + 0.005)

        } else if(myLocation.latitude < placeLocation.latitude && myLocation.longitude >= placeLocation.longitude){

            boundMyLocation = LatLng(myLocation.latitude - 0.005,myLocation.longitude - 0.005)
            boundPlaceLocation = LatLng(placeLocation.latitude + 0.005, placeLocation.longitude - 0.005)

        }else{

            boundMyLocation = LatLng(myLocation.latitude - 0.005,myLocation.longitude - 0.005)
            boundPlaceLocation = LatLng(placeLocation.latitude + 0.005, placeLocation.longitude + 0.005)

        }

        var bound = LatLngBounds(boundMyLocation, boundPlaceLocation)

        val cameraUpdate = CameraUpdate.fitBounds(bound)
        naverMap.moveCamera(cameraUpdate)

        val distance = myLocation.distanceTo(placeLocation) / 1000
        myLocationMarker.icon = MarkerIcons.BLACK
        myLocationMarker.iconTintColor = Color.rgb(254,32,32)
        val dis = findViewById<TextView>(R.id.location_dis)
        val name = findViewById<TextView>(R.id.location_name)
        val loc = findViewById<TextView>(R.id.location_loc)
        val time = findViewById<TextView>(R.id.location_time)
        name.setText(place_list.get(index).place_name)
        loc.setText(place_list.get(index).loc)
        time.setText( String.format("%.2f", distance / 0.08 ) + " min")
        dis.setText(String.format("%.2f", distance) + "km")

    }
}