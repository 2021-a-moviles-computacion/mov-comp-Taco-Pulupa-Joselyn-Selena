package com.example.examen_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.examen_02.dto.ProveedorDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarProveedor : AppCompatActivity() {
    var valoruid=""
    var idFormulario = ""
    var nombreFormulario = ""
    var sueldoFormulario = ""
    var fechaFormulario = ""
    var estadoFormulario = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_proveedor)

        val Proveedor = intent.getParcelableExtra<ProveedorDto>("Proveedor")

        val id = findViewById<TextView>(R.id.txt_id)
        val nombre = findViewById<EditText>(R.id.txv_nombre)
        val cedula = findViewById<EditText>(R.id.txv_cedula)
        val sueldo = findViewById<EditText>(R.id.txv_sueldo)
        val fecha = findViewById<EditText>(R.id.txv_fecha)
        val activo = findViewById<RadioButton>(R.id.rb_activo)
        val pasivo = findViewById<RadioButton>(R.id.rb_pasivo)
        val grup = findViewById<RadioGroup>(R.id.rbg_estado)

        if (Proveedor != null) {
            valoruid = Proveedor.uid
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
            nombreFormulario = nombre.text.toString()
            sueldoFormulario = sueldo.text.toString()
            fechaFormulario = fecha.text.toString()
            estadoFormulario = estado()

            editarProveedor(nombreFormulario,sueldoFormulario,fechaFormulario,estadoFormulario)
            id.setText("")
            nombre.setText("")
            cedula.setText("")
            sueldo.setText("")
            fecha.setText("")
        }

    }

    fun editarProveedor(nombre:String, sueldo:String, fecha: String,estado: String){
        val Proveedor = intent.getParcelableExtra<ProveedorDto>("Proveedor")

        //val estado = estado()

        val db = Firebase.firestore
        val referencia = db.collection("proveedor").document(valoruid)

        db.runTransaction {transaction ->
            val documentoActual = transaction.get(referencia)
            val nomb = documentoActual.getString("nombre")
            val suel = documentoActual.getString("sueldo")
            if(nombre != nomb){
                transaction.update(referencia,"nombre",nombre)
            }
            if(sueldo != suel){
                transaction.update(referencia,"sueldo",sueldo)
            }
            if(fecha != Proveedor?.fecha){
                transaction.update(referencia,"fecha",fecha)
            }
            if(estado != Proveedor?.estado){
                transaction.update(referencia,"estado",estado)
            }

        }
            .addOnSuccessListener { Log.i("transaccion","Transaccion competada") }
            .addOnFailureListener { Log.i("transaccion","ERROR") }
    }

    fun estado(): String {
        val activo = findViewById<RadioButton>(R.id.rb_activo)
        val pasivo = findViewById<RadioButton>(R.id.rb_pasivo)
        var estado:String = "0"
        if(activo.isChecked  == true){
            estado = "1"
        }else if (pasivo.isChecked  == true){
            estado = "0"
        }
        return estado
    }
}