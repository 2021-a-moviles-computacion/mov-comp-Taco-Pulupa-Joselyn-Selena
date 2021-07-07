package com.example.android_moviles_2020_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CrudBaseDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_base_datos)

        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)
        Log.i("bdd", EBaseDeDatos.TablaUsuario!!.databaseName.toString())

        val nombre = findViewById<EditText>(R.id.txt_nombre)
        val descripcion = findViewById<EditText>(R.id.txt_descripcion)
        val id = findViewById<EditText>(R.id.txt_id)

        val botonAgregarUsuario = findViewById<Button>(
            R.id.btn_agregar
        )
        botonAgregarUsuario.setOnClickListener {
            if(EBaseDeDatos.TablaUsuario != null){
                EBaseDeDatos.TablaUsuario!!.crearUsuarioFormulario(
                    nombre.text.toString(),
                    descripcion.text.toString()
                )
                Log.i("bdd", "${nombre.text} y ${descripcion.text}")
            }
            id.setText("")
            nombre.setText("")
            descripcion.setText("")
        }

        val botonBuscarUsuario = findViewById<Button>(
            R.id.btn_buscar
        )
        botonBuscarUsuario.setOnClickListener {
            if(EBaseDeDatos.TablaUsuario != null){
                val usuario = EBaseDeDatos.TablaUsuario!!.consultarUsuarioPorId(id.text.toString().toInt())
                Log.i("bdd", "${usuario.nombre} y ${usuario.descripcion}")
            }
            id.setText("")
            nombre.setText("")
            descripcion.setText("")
        }

        val botonActualizarUsuario = findViewById<Button>(
            R.id.btn_actualizar
        )
        botonActualizarUsuario.setOnClickListener {
            if(EBaseDeDatos.TablaUsuario != null){
                EBaseDeDatos.TablaUsuario!!.actualizarUsuaruiFormulario(
                    nombre.text.toString(),
                    descripcion.text.toString(),
                    id.text.toString().toInt()
                )
                Log.i("bdd", "${nombre.text} y ${descripcion.text}")
            }
            id.setText("")
            nombre.setText("")
            descripcion.setText("")
        }

        val botonEliminarUsuario = findViewById<Button>(
            R.id.btn_eliminar
        )
        botonEliminarUsuario.setOnClickListener {
            if(EBaseDeDatos.TablaUsuario != null){
                EBaseDeDatos.TablaUsuario!!.eliminarUsuarioPorId(id.text.toString().toInt())
                Log.i("bdd", "${nombre.text} y ${descripcion.text}")
            }
            id.setText("")
            nombre.setText("")
            descripcion.setText("")
        }
    }
}