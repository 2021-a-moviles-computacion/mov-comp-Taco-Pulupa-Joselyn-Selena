package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class EditarProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        val Producto = intent.getParcelableExtra<ProductoBDD>("producto")

        val id = findViewById<TextView>(R.id.tv_id_producto)
        val nombre = findViewById<EditText>(R.id.txv_nombre_producto)
        val descripcion = findViewById<EditText>(R.id.txv_descripcion_producto)
        val precio = findViewById<EditText>(R.id.txv_precio)
        val total = findViewById<EditText>(R.id.txv_total_producto)
        val fecha = findViewById<EditText>(R.id.txv_fecha_producto)
        var idProveedor = ""

        if (Producto != null) {
            id.setText("${Producto.id}")
            nombre.setText("${Producto.nombre}")
            descripcion.setText("${Producto.descripcion}")
            precio.setText("${Producto.precio}")
            total.setText("${Producto.total}")
            fecha.setText("${Producto.fecha}")
            idProveedor = Producto.idProveedor.toString()
        }

        val botonActualizarProducto = findViewById<Button>(
            R.id.btn_actualizar_producto
        )
        botonActualizarProducto.setOnClickListener {
            if(ProBaseDeDatos.TablaProducto != null){
                ProBaseDeDatos.TablaProducto!!.actualizarProductoFormulario(
                    nombre.text.toString(),
                    descripcion.text.toString(),
                    precio.text.toString(),
                    total.text.toString(),
                    fecha.text.toString(),
                    id.text.toString()
                )
                Log.i("bdd", "${nombre.text} -- ${precio.text}")
            }
            id.setText("")
            nombre.setText("")
            descripcion.setText("")
            precio.setText("")
            total.setText("")
            fecha.setText("")
        }

    }
}