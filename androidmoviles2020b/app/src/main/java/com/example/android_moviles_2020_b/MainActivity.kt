package com.example.android_moviles_2020_b

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)
//        if(EBaseDeDatos.TablaUsuario != null){
//            EBaseDeDatos.TablaUsuario.consultarUsuarioPorId()
//            EBaseDeDatos.TablaUsuario.crearUsuarioFormulario()
//            EBaseDeDatos.TablaUsuario.eliminarUsuarioPorId()
//            EBaseDeDatos.TablaUsuario.actualizarUsuaruiFormulario()
//        }

        val botonIrACicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonIrACicloVida.setOnClickListener{abrirActividad(ACicloVida::class.java)}

        val botonIrListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )
        botonIrListView.setOnClickListener{abrirActividad(BListView::class.java)}

        val botonIrIntent = findViewById<Button>(
            R.id.btn_ir_intent
        )
        botonIrIntent.setOnClickListener{abrirActividadConParametros(CIntentExplicitoParametros::class.java)}

        val botonAbrirIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )
        botonAbrirIntentExplicito.setOnClickListener{
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            startActivityForResult(intentConRespuesta,CODIGO_RESPUESTA_INTENT_IMPLICITO)
        }

        val botonIrBaseDeDatos = findViewById<Button>(
            R.id.btn_base_datos
        )
        botonIrBaseDeDatos.setOnClickListener{abrirActividad(CrudBaseDatos::class.java)}

        val botonAbrirRecyclerView = findViewById<Button>(
            R.id.btn_ir_recycler_view
        )
        botonAbrirRecyclerView.setOnClickListener{abrirActividad(GRecyclerView::class.java)}
    }

    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        // this.startActivity(intent)
        startActivity(intentExplicito)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )

        intentExplicito.putExtra("nombre", "Adrian")
        intentExplicito.putExtra("apellido", "Eguez")
        intentExplicito.putExtra("edad", 23)
        intentExplicito.putExtra(
            "entrenador",
            BEntrenador("Joselyn","Taco")
        )
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
     /*   registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                Activity.RESULT_OK ->{
                    //Ejecutar codigo OK
                    it.data?.getStringExtra("nombreModificado")
                    it.data?.getIntExtra("edadModificado",0)
                    it.data?.getParcelableExtra<BEntrenador>("entrenadorModificado")
                }
            }
        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO ->{
                if (resultCode == RESULT_OK){
                    Log.i("intent-explicito", "Si actualizado los datos")
                    if (data != null){
                        val nombre = data.getStringExtra("nombreModificado")
                        val edad = data.getIntExtra("edadModificado",0)
                        val entrenador = data.getParcelableExtra<BEntrenador>("entrenadorModificado")

                        Log.i("intent-explicito", "${nombre}")
                        Log.i("intent-explicito", "${edad}")
                        Log.i("intent-explicito", "${entrenador}")
                    }
                }
            }
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> {
                if (resultCode == RESULT_OK){
                    if (data != null){
                        if (data.data != null) {
                            val uri: Uri = data.data!!
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getShort(
                                // la doble exclamasion significa, que no importa que tu veas que es nullo no es
                                indiceTelefono!!
                            )
                            cursor?.close()
                            Log.i("resultado", "Telefono ${telefono}")
                        }
                    }
                }
            }
        }
    }
}