package com.example.android_moviles_2020_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = arrayListOf<Int>(1,2,3)
        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            arregloNumeros // arreglo
        )

        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter = adaptador

        val botonAñadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAñadirNumero.setOnClickListener { añadirItemsAlListView(
            1,
            arregloNumeros,
            adaptador
        ) }

        //listViewEjemplo
          //  .setOnItemLongClickListener { adapterView, view, posicion, id ->
            //Log.i("list-view","Dio Click ${posicion}")
             //   return@setOnItemLongClickListener true
        //}
        registerForContextMenu(listViewEjemplo)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflanter = menuInflater
        inflanter.inflate(R.menu.menu, menu)
    }

    fun añadirItemsAlListView(
        valor: Int,
        arreglo: ArrayList<Int>,
        adaptador: ArrayAdapter<Int>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()//actualiza la interfaz
    }


}