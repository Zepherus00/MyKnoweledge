package com.skillbox.lesson_18

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.skillbox.lesson_18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var locationListener: LocationSource.OnLocationChangedListener? = null
    private var map: GoogleMap? = null

    private lateinit var fusedClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding

    private var needAnimateCamera = false
    private var needMoveCamera = true

    private val handler = Handler(Looper.getMainLooper())
    private val cameraMovedRunnable = Runnable {
        needMoveCamera = true
    }

    private val launcher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map.values.isNotEmpty() && map.values.all { it }) {
            startLocation()
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let { location ->
                locationListener?.onLocationChanged(location)

                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    LatLng(location.latitude, location.longitude),
                    18f
                )
                if (needMoveCamera) {
                    if (needAnimateCamera) {
                        map?.animateCamera(cameraUpdate)
                    } else {
                        needAnimateCamera = true
                        map?.moveCamera(cameraUpdate)
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocation() {
        map?.isMyLocationEnabled = true
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()

        fusedClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mapOverlay.setOnTouchListener { _, _ ->
            handler.removeCallbacks(cameraMovedRunnable)
            needMoveCamera = false
            handler.postDelayed(cameraMovedRunnable, 5000)
            false
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            map = googleMap
            checkPermission()
            with(googleMap.uiSettings) {
                isZoomControlsEnabled = true
                isMyLocationButtonEnabled = true
            }
            googleMap.setLocationSource(object : LocationSource {
                override fun activate(p0: LocationSource.OnLocationChangedListener) {
                    locationListener = p0
                }

                override fun deactivate() {
                    locationListener = null
                }
            })
        }

        fusedClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    override fun onStop() {
        super.onStop()
        fusedClient.removeLocationUpdates(locationCallback)
        needAnimateCamera = false
    }

    private fun checkPermission() {
        if (REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            startLocation()
        } else {
            launcher.launch(REQUIRED_PERMISSIONS)
        }
    }

    companion object {
        private val REQUIRED_PERMISSIONS: Array<String> = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}