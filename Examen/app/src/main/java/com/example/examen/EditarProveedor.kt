package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class EditarProveedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_proveedor)

        val Proveedor = intent.getParcelableExtra<ProveedorBDD>("proveedor")

        val id = findViewById<TextView>(R.id.txt_id)
        val nombre = findViewById<EditText>(R.id.txv_nombre)
        val cedula = findViewById<EditText>(R.id.txv_cedula)
        val sueldo = findViewById<EditText>(R.id.txv_sueldo)
        val fecha = findViewById<EditText>(R.id.txv_fecha)
        val activo = findViewById<RadioButton>(R.id.rb_activo)
        val pasivo = findViewById<RadioButton>(R.id.rb_pasivo)
        val grup = findViewById<RadioGroup>(R.id.rbg_estado)

        if (Proveedor != null) {
            id.setText("${Proveedor.id}")
            nombre.setText("${Proveedor.nombre}")
            cedula.setText("${Proveedor.cedula}")
            cedula.isEnabled
            sueldo.setText("${Proveedor.sueldo}")
            fecha.setText("${Proveedor.fecha}")
            if(Proveedor.estado.toString() == "1"){
                grup.check(activo.id)
            }else{
                grup.check(pasivo.id)
            }
        }

        val botonActualizarUsuario = findViewById<Button>(
            R.id.btn_actualizar
        )
        botonActualizarUsuario.setOnClickListener {
            if(EBaseDeDatos.TablaProveedor != null){
                EBaseDeDatos.TablaProveedor!!.actualizarUsuaruiFormulario(
                    nombre.text.toString(),
                    cedula.text.toString(),
                    sueldo.text.toString(),
                    fecha.text.toString(),
                    estado(),
                    id.text.toString().toInt()
                )
                Log.i("bdd", "${nombre.text}")
            }
            id.setText("")
            nombre.setText("")
            cedula.setText("")
            sueldo.setText("")
            fecha.setText("")
        }

    }
    fun estado(): String {
        val activo = findViewById<RadioButton>(R.id.rb_activo)
        val pasivo = findViewById<RadioButton>(R.id.rb_pasivo)
        var estado:String = "0"
        if(activo.isSelected() == true){
            estado = "1"
        }else if (pasivo.isSelected() == true){
            estado = "0"
        }
        return estado
    }
}