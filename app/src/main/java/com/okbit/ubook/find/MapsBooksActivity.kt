package com.okbit.ubook.find

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Float2
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.okbit.ubook.R
import java.util.jar.Manifest
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
class MapsBooksActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private  val zoneBooks = mutableListOf<ZoneBooks>()
    private val userLocation = Location("")
    private lateinit var myLocationButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_books)
        title = "Zonas de Entrega"

        myLocationButton = findViewById(R.id.my_location_button)

        addZoneBooks() //lista de mapas

        requestLocationPermission()
    }

    private fun addZoneBooks() {
        zoneBooks.add(ZoneBooks("Zona 1 Montevideo Shopping", -34.9031961, -56.1382969))
        zoneBooks.add(ZoneBooks("Zona 2 Tres Cruces Shopping", -34.8938207, -56.1685413))
        zoneBooks.add(ZoneBooks("Zona 3 Nuevo Centro Shopping", -34.8689368, -56.1719842))
        zoneBooks.add(ZoneBooks("Zona 4 Punta Carreta Shopping", -34.9241177, -56.1607743))
        zoneBooks.add(ZoneBooks("Zona 5 Portones Shopping", -34.8811342, -56.083531))

    }

    private fun requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getUserLocation()
            } else {
                val permissionArray = arrayOf(ACCESS_FINE_LOCATION)
                requestPermissions(permissionArray, LOCATION_PERMISSION_REQUEST_CODE)
            }

        } else {
            getUserLocation()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getUserLocation()
            } else if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                showLocationPermissionRationaleDialog()
            } else {
                finish()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showLocationPermissionRationaleDialog() {
        val dialog = AlertDialog.Builder(this).setTitle("Necesitas permiso de ubicción")
            .setMessage("Aceptar permiso para poder ubicar libros por Zonas de Entrega")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                requestPermissions(arrayOf(ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
            }.setNegativeButton("No") {_, _ ->
                finish()
            }
        dialog.show()
    }

    @SuppressLint("MissingPermission")
    private  fun getUserLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            location : Location? ->
            if (location != null) {
                userLocation.latitude = location.latitude
                userLocation.longitude = location.longitude
                setupMap()
            }
        }
    }

    private fun setupMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val icon = getBookIcon()

        //iteracion lista de zonas
        for (zoneBook in zoneBooks) {
            val zonePosition = LatLng(zoneBook.latitude, zoneBook.longitude)
            val zoneName = zoneBook.name

            val bookLocation = Location("")
            bookLocation.latitude = zoneBook.latitude
            bookLocation.longitude = zoneBook.longitude

            val distance = bookLocation.distanceTo(userLocation).toInt()

            val markerOptions = MarkerOptions().position(zonePosition).title(zoneName)
                .snippet("Distancia: $distance mts")
                .icon(icon)
            mMap.addMarker(markerOptions)
        }

        // Add a marker in Zoo Villa Dolores and move the camera
        val mvd = LatLng(userLocation.latitude, userLocation.longitude)
        mMap.addMarker(MarkerOptions().position(mvd).title("Estás aquí"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mvd, 13.0f))

        myLocationButton.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mvd, 13.0f))
        }
    }

    private fun getBookIcon(): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(this, R.drawable.icon40px)
        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(drawable?.intrinsicWidth ?: 0,
            drawable?.intrinsicHeight ?: 0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable?.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}