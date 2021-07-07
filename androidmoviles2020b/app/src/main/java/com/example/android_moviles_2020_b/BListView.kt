package com.example.android_moviles_2020_b

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {

    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = BBaseDatosMemoria.arregloBEntrenador

        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            arregloNumeros // arreglo
        )

        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter = adaptador

        val botonAñadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAñadirNumero.setOnClickListener { añadirItemsAlListView(
            BEntrenador("Prueba","d@d.com"),//////
            arregloNumeros,
            adaptador
        ) }

        listViewEjemplo
            .setOnItemLongClickListener { adapterView, view, posicion, id ->
            Log.i("list-view","Dio Click ${posicion}")
                val builder = AlertDialog.Builder(this)

                builder.setTitle("Titulo")
                //builder.setMessage("Mensaje")

                val seleccionUsuario = booleanArrayOf(
                    true,
                    false,
                    false
                )

                val opciones = resources.getStringArray(R.array.string_array_optiones_dialogo)

                builder.setMultiChoiceItems(
                    opciones,
                    seleccionUsuario,
                    { dialog, which, isChecked ->
                        Log.i("list-view","${which} ${isChecked}")
                    }
                )

                builder.setPositiveButton(
                    "si",
                    DialogInterface.OnClickListener { dialog, which ->
                        Log.i("list-view","Si")
                    }
                )

                builder.setNegativeButton(
                    "no",
                    null
                )
                val dialogo = builder.create()
                dialogo.show()

                return@setOnItemLongClickListener true
        }
       // registerForContextMenu(listViewEjemplo)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflanter = menuInflater
        inflanter.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("List-view", "List View ${posicionItemSeleccionado}")
        Log.i("List-view", "Entrenador ${BBaseDatosMemoria.arregloBEntrenador[id]}")

    }

    fun añadirItemsAlListView(
        valor: BEntrenador,
        arreglo: ArrayList<BEntrenador>,
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()//actualiza la interfaz
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //Editar
                R.id.mi_editar ->{
                    Log.i("List-view", "Editar ${BBaseDatosMemoria.arregloBEntrenador[
                            posicionItemSeleccionado
                    ]}")
                    return true
                }

            //Eliminar
            R.id.mi_eliminar ->{
                Log.i("List-view", "Eliminar ${BBaseDatosMemoria.arregloBEntrenador[
                        posicionItemSeleccionado
                ]}")
                return true
            }
            else ->  super.onContextItemSelected(item)
        }
    }

}