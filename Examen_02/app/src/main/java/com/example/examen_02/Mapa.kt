package com.example.examen_02

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.examen_02.dto.ProductoDto
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Mapa : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        val Proveedor = intent.getStringExtra("idProveedor")

        val latitud = findViewById<EditText>(R.id.edt_latitud)
        val longitud = findViewById<EditText>(R.id.edt_longitud)
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_ubicacion)

        solicitarPermisos()
        val fragmentoMapa= supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            if (googleMap != null) {
                mapa = googleMap
                establecerConfiguracionMapa()
                var ProductoArray = intent.getParcelableExtra<ProductoDto>("Producto")

                if(ProductoArray?.latitudText != null) {
                    latitud.setText(ProductoArray?.latitudText.toString())
                    longitud.setText(ProductoArray?.longitudText.toString())

                    val quicentro = LatLng(
                        ProductoArray?.latitudText!!.toDouble(),
                        ProductoArray?.longitudText!!.toDouble()
                    )
                    //val quicentro = LatLng(-0.176125, -78.480208)
                    val titulo = ProductoArray.nombre
                    val zoom = 17f
                    añadirMArcador(quicentro, titulo)
                    moverCamaraConZoom(quicentro, zoom)

                    botonGuardar.visibility = View.INVISIBLE
                    latitud.setEnabled(false)
                    longitud.setEnabled(false)

                }
                escucharListener()
            }
        }

        botonGuardar.setOnClickListener {
            irActividad(CrearProducto::class.java,arrayListOf(Pair("Ubicacion",ProductoDto(
                "0","0","0","0",
                "0","0","0",Proveedor.toString(),
                latitud.text.toString(),longitud.text.toString()))))
        }

    }

    fun irActividad(
        clase: Class<*>,
        parametros: ArrayList<Pair<String, *>>? = null,
        codigo: Int? = null
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        parametros?.forEach {
            val nombreVariable = it.first
            val valorVariable: Any? = it.second

            when (it.second) {
                is String -> {
                    valorVariable as String
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                is Parcelable -> {
                    valorVariable as Parcelable
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                is Int -> {
                    valorVariable as Int
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                else -> {
                    valorVariable as String
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
            }


        }

        if (codigo != null) {
            startActivityForResult(intentExplicito, codigo)
        } else {
            startActivity(intentExplicito)

        }
    }

    fun escucharListener(){
        val latitud = findViewById<EditText>(R.id.edt_latitud)
        val longitud = findViewById<EditText>(R.id.edt_longitud)

        mapa.setOnMapClickListener {
            latitud.setText(it.latitude.toString())
            longitud.setText(it.longitude.toString())
            Log.i("mapa","setOnMapClickListener${it}")
        }

    }

    fun añadirMArcador(latLng: LatLng, title:String){
        mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )
    }

    fun moverCamaraConZoom(latLng: LatLng,zoom: Float = 10f){
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