package com.example.firebase

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.type.LatLng

class FMapsActivity : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fmaps)
        solicitarPermisos()
        val fragmentoMapa= supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            if(googleMap != null){
                mapa = googleMap
                establecerConfiguracionMapa()
                val quicentro = com.google.android.gms.maps.model.LatLng(-0.176125, -78.480208)
                val titulo = "Quicentro"
                val zoom = 17f
                añadirMArcador(quicentro,titulo)
                moverCamaraConZoom(quicentro,zoom)

                //LINEA
                val poliLineaUno = googleMap
                    .addPolyline(
                        PolylineOptions()
                            .clickable(true)
                            .add(
                                com.google.android.gms.maps.model.LatLng(-0.1759187040647396, -78.48506472421384),
                                com.google.android.gms.maps.model.LatLng(-0.17632468492901104, -78.48265589308046),
                                com.google.android.gms.maps.model.LatLng(-0.17746143130181483, -78.4770533307815)
                            )
                    )
                poliLineaUno.tag = "Linea-1" // <- ID

                //POLIGONO
                val poLigonoUno = googleMap
                    .addPolygon(
                        PolygonOptions()
                            .clickable(true)
                            .add(
                                com.google.android.gms.maps.model.LatLng(-0.1771546902239471, -78.48344981495214),
                                com.google.android.gms.maps.model.LatLng(-0.17968981486125768, -78.48269198043828),
                                com.google.android.gms.maps.model.LatLng(-0.17710958124147777, -78.48142892291516)
                            )
                    )
                poLigonoUno.fillColor = -0xc771c4
                poLigonoUno.tag = "Poligono-2" //<- ID

                escucharListener()

            }
        }

        val botonCarolina = findViewById<Button>(R.id.btn_ir_carolina)
        botonCarolina.setOnClickListener {
            val carolina = com.google.android.gms.maps.model.LatLng(-0.18288452555103193, -78.48449971346241)
            val zoom = 17f
            moverCamaraConZoom(carolina,zoom)
        }

    }

    fun escucharListener(){
        mapa.setOnPolygonClickListener {
            Log.i("mapa","setOnPolygonClickListener${it}")
        }

        mapa.setOnPolylineClickListener {
            Log.i("mapa","setOnPolylineClickListener${it}")
        }

        mapa.setOnMarkerClickListener {
            Log.i("mapa","setOnMarkerClickListener${it}")
            return@setOnMarkerClickListener true
        }

        mapa.setOnCameraMoveListener {
            Log.i("mapa","setOnCameraMoveListener")
        }

        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa","setOnCameraMoveStartedListener${it}")
        }

        mapa.setOnCameraIdleListener {
            Log.i("mapa","setOnCameraIdleListener")
        }

        mapa.setOnMapClickListener {
            Log.i("mapa","setOnMapClickListener${it}")
        }

        mapa.setOnMyLocationClickListener {
            Log.i("mapa","setOnMyLocationClickListener${it}")
        }
    }

    fun añadirMArcador(latLng: com.google.android.gms.maps.model.LatLng, title:String){
        mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    fun moverCamaraConZoom(latLng: com.google.android.gms.maps.model.LatLng,zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng,zoom)
        )
    }

    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if(tienePermisos){
            permisos = true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1//Codigo de peticion de los permisos
            )
        }
    }

    fun establecerConfiguracionMapa(){

        val contexto = this.applicationContext

        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }

    }

}