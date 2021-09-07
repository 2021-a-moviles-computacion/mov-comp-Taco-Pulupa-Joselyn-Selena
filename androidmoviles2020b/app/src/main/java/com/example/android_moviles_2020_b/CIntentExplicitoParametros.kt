package com.example.android_moviles_2020_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0 )

        val entrenador = intent.getParcelableExtra<BEntrenador>("entrenador")

        Log.i("intent-explicito","${nombre}")
        Log.i("intent-explicito","${apellido}")
        Log.i("intent-explicito","${edad}")
        Log.i("intent-explicito","${entrenador}")

        val botonDevolverRespuesta = findViewById<Button>(R.id.btn_devolver_respuesta)

        botonDevolverRespuesta
            .setOnClickListener {
                val intentDevolverParametos = Intent()
                intentDevolverParametos.putExtra("nombreModificado","Vicente")
                intentDevolverParametos.putExtra("edeModificado",33)
                intentDevolverParametos.putExtra(
                    "entrenadorModificado",
                    BEntrenador("Vicente","Sarzosa")
                )
                //this.setResult(
                setResult(
                    RESULT_OK,
                    intentDevolverParametos
                )
                //se pone un finish para la actividad cuando se envian para metros
                finish()
                //this.finish(
            }
    }
}