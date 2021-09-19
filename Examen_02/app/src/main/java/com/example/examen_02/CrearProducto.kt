package com.example.examen_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.examen_02.dto.ProductoDto
import com.example.examen_02.dto.ProveedorDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearProducto : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        val longitudText = findViewById<TextView>(R.id.txv_longitud)
        val latitudText = findViewById<TextView>(R.id.txv_latitud)

        val Producto = intent.getParcelableExtra<ProductoDto>("Ubicacion")

        latitudText.setText(Producto?.latitudText)
        longitudText.setText(Producto?.longitudText)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_producto)
        val Proveedor = intent.getStringExtra("Proveedor")

        val botonAgregarCoordenadas = findViewById<Button>(R.id.btn_mapa)
        botonAgregarCoordenadas.setOnClickListener {
            irActividad(Mapa::class.java, arrayListOf(Pair("idProveedor",Proveedor)))
        }

        val botonAgregarProducto = findViewById<Button>(R.id.btn_registrar_producto)
        botonAgregarProducto.setOnClickListener {
            crearProducto()
            irActividad(Productos::class.java)
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

    fun crearProducto(){
        val ProductoArray = intent.getParcelableExtra<ProductoDto>("Ubicacion")

        val nombre = findViewById<EditText>(R.id.txt_nombre_producto)
        val descripcion = findViewById<EditText>(R.id.txt_descripcion)
        val precio = findViewById<EditText>(R.id.txt_precio)
        val total = findViewById<EditText>(R.id.txt_total)
        val fecha = findViewById<EditText>(R.id.txt_fechaV)
        val longitudText = findViewById<TextView>(R.id.txv_longitud)
        val latitudText = findViewById<TextView>(R.id.txv_longitud)
        val idProv = ProductoArray?.idProveedor

        val nuevoProducto = hashMapOf<String,Any>(
            "nombre" to nombre.text.toString(),
            "descripcion" to descripcion.text.toString(),
            "precio" to precio.text.toString(),
            "total" to total.text.toString(),
            "fecha" to fecha.text.toString(),
            "idProv" to idProv.toString(),
            "longitudText" to ProductoArray?.longitudText.toString(),
            "latitudText" to ProductoArray?.latitudText.toString()
        )
        val db = Firebase.firestore
        val referencia = db.collection("producto")
        referencia
            .add(nuevoProducto)
            .addOnSuccessListener {
                nombre.text.clear()
                descripcion.text.clear()
                precio.text.clear()
                total.text.clear()
                fecha.text.clear()
                longitudText.setText("")
                latitudText.setText("")
            }
            .addOnFailureListener {

            }
    }
}