package com.okbit.ubook.find

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.okbit.ubook.R


class MapsBooksActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private  val zoneBooks = mutableListOf<ZoneBooks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_books)

        addFakeZoneBooks() //lista de mapas
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun addFakeZoneBooks() {
        zoneBooks.add(ZoneBooks("zona 1 Montevideo Shopping", -34.9031961, -56.1382969))
        zoneBooks.add(ZoneBooks("zona 2 Tres Cruces Shopping", -34.8938207, -56.1685413))
        zoneBooks.add(ZoneBooks("zona 3 Nuevo Centro Shopping", -34.8689368, -56.1719842))
        zoneBooks.add(ZoneBooks("zona 4 Punta Carreta Shopping", -34.9241177, -56.1607743))
        zoneBooks.add(ZoneBooks("zona 5 Portones Shopping", -34.8811342, -56.083531))

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

            val markerOptions = MarkerOptions().position(zonePosition).title(zoneName).icon(icon)
            mMap.addMarker(markerOptions)
        }

        // Add a marker in Zoo Villa Dolores and move the camera
        val villa = LatLng(-34.9007339, -56.1474546)
        mMap.addMarker(MarkerOptions().position(villa).title("Villa dolores"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(villa, 13.0f))
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