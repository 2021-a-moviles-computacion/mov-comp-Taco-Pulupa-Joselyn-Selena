package com.example.examen_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearProveedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_proveedor)

        val botonAgregarProveedor = findViewById<Button>(R.id.btn_reguistrar)
        botonAgregarProveedor.setOnClickListener {
            crearProveedor()
            irActividad(MainActivity::class.java)
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

    fun estado(): String {
        val activo = findViewById<RadioButton>(R.id.rdb_activo)
        val pasivo = findViewById<RadioButton>(R.id.rdb_pasivo)
        var estado:String = "0"
        if(activo.isChecked == true){
            estado = "1"
        }else if (pasivo.isChecked == true){
            estado = "0"
        }

        return estado
    }

    fun crearProveedor(){
        val nombre = findViewById<EditText>(R.id.txt_name)
        val cedula = findViewById<EditText>(R.id.txt_cedula)
        val sueldo = findViewById<EditText>(R.id.txt_sueldo)
        val fecha = findViewById<EditText>(R.id.txt_fecha)
        val estado = estado()

        val nuevoProducto = hashMapOf<String,Any>(
            "nombre" to nombre.text.toString(),
            "cedula" to cedula.text.toString(),
            "sueldo" to sueldo.text.toString(),
            "fecha" to fecha.text.toString(),
            "estado" to estado
        )
        val db = Firebase.firestore
        val referencia = db.collection("proveedor")
        referencia
            .add(nuevoProducto)
            .addOnSuccessListener {
                nombre.text.clear()
                cedula.text.clear()
                sueldo.text.clear()
                fecha.text.clear()
            }
            .addOnFailureListener {}
    }
}