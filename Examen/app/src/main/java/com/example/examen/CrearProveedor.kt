package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class CrearProveedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_proveedor)

        EBaseDeDatos.TablaProveedor = SqliteProveedor(this)
        Log.i("bdd", EBaseDeDatos.TablaProveedor!!.databaseName.toString())

        val nombre = findViewById<EditText>(R.id.txt_name)
        val cedula = findViewById<EditText>(R.id.txt_cedula)
        val sueldo = findViewById<EditText>(R.id.txt_sueldo)
        val fecha = findViewById<EditText>(R.id.txt_fecha)
        val activo = findViewById<RadioButton>(R.id.rdb_activo)
        val pasivo = findViewById<RadioButton>(R.id.rdb_pasivo)

        val botonAgregarUsuario = findViewById<Button>(
            R.id.btn_reguistrar
        )
        botonAgregarUsuario.setOnClickListener {
            if(EBaseDeDatos.TablaProveedor != null){
                EBaseDeDatos.TablaProveedor!!.crearUsuarioFormulario(
                    nombre.text.toString(),
                    cedula.text.toString(),
                    sueldo.text.toString(),
                    fecha.text.toString(),
                    estado()
                )
                Log.i("bdd", "${nombre.text}")
            }
            nombre.setText("")
            cedula.setText("")
            sueldo.setText("")
            fecha.setText("")
        }

    }

    fun estado(): String {
        val activo = findViewById<RadioButton>(R.id.rdb_activo)
        val pasivo = findViewById<RadioButton>(R.id.rdb_pasivo)
        var estado:String = "0"
        if(activo.isSelected() == true){
            estado = "1"
        }else if (pasivo.isSelected() == true){
            estado = "0"
        }
        return estado
    }
}