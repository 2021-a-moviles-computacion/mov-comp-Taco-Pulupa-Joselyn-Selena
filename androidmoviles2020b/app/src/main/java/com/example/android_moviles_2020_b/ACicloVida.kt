package com.example.android_moviles_2020_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ACicloVida : AppCompatActivity() {

    var numero = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        Log.i("ciclo-vida","onCreate")
        val textViewCicloVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewCicloVida.text = numero.toString()
        val buttonAClicoVida = findViewById<Button>(
            R.id.btn_aumentar_ciclo_vida
        )
        buttonAClicoVida.setOnClickListener { aumentarNumero() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            //Aqui guardamos
            //cualquier primitivo - solo primitivos
            putInt("numeroGuardado", numero)
        }
        super.onSaveInstanceState(outState)
        Log.i("ciclo-vida","onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado: Int? = savedInstanceState.getInt("numeroGuardado")
        if(numeroRecuperado != null){
            Log.i("ciclo-vida", "Llego numero ~${numeroRecuperado}")
            //this.numero = numero
            numero = numeroRecuperado
            val textViewACicloVida = findViewById<TextView>(
                R.id.txv_ciclo_vida
            )
            textViewACicloVida.text = numero.toString()
        }
        Log.i("ciclo-vida","onRestoreInstanceState")
    }

    fun aumentarNumero(){
        numero = numero + 1
        val textViewACicloVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        textViewACicloVida.text = numero.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida","onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida","onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida","onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida","onPause")
    }
}