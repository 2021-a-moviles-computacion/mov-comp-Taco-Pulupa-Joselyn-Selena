package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class CrearProducto: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_producto)
        val idProveedor = intent.getIntExtra("idProveedor",0)

        ProBaseDeDatos.TablaProducto = SqliteProductos(this)
        Log.i("bdd", "llegoProducto con id ${idProveedor}")

        val nombre = findViewById<EditText>(R.id.txt_nombre_producto)
        val descripcion = findViewById<EditText>(R.id.txt_descripcion)
        val precio = findViewById<EditText>(R.id.txt_precio)
        val total = findViewById<EditText>(R.id.txt_total)
        val fecha = findViewById<EditText>(R.id.txt_fechaV)
        val idProv = idProveedor

        val botonAgregarProducto = findViewById<Button>(
            R.id.btn_registrar_producto
        )
        botonAgregarProducto.setOnClickListener {
            if(ProBaseDeDatos.TablaProducto != null){
                ProBaseDeDatos.TablaProducto!!.crearProducto(
                    nombre.text.toString(),
                    descripcion.text.toString(),
                    precio.text.toString(),
                    total.text.toString(),
                    fecha.text.toString(),
                    idProv.toString()
                )
                Log.i("bdd", "${nombre.text}")
            }
            nombre.setText("")
            descripcion.setText("")
            precio.setText("")
            total.setText("")
            fecha.setText("")
        }

    }
}