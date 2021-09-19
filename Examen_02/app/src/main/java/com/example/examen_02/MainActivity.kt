package com.example.examen_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.examen_02.dto.ProductoDto
import com.example.examen_02.dto.ProveedorDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var arregloProducto = arrayListOf<ProductoDto>()

    var arregloProveedores = arrayListOf<ProveedorDto>()
    var adaptadorProveedores: ArrayAdapter<ProveedorDto>? = null
    var proveedorSeleccionado: ProveedorDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listViewRestaurantes = findViewById<ListView>(R.id.ltv_proveedores)

        if(adaptadorProveedores == null) {
            adaptadorProveedores = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                arregloProveedores
            )
            adaptadorProveedores?.setDropDownViewResource(android.R.layout.simple_list_item_1)
            cargarProveedor()
        }

        val botonCrear = findViewById<Button>(
            R.id.btn_crear
        )
        botonCrear.setOnClickListener { irActividad(CrearProveedor::class.java) }
        registerForContextMenu(listViewRestaurantes)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflanter = menuInflater
        inflanter.inflate(R.menu.men_proveedor, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
    }

    fun irActividad(
        clase: Class<*>,
        parametros: ArrayList<Pair<String, *>>? = null,
        codigo: Int? = null
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        parametros?.forEach {
            val nombreVariable = it.first
            val valorVariable: Any? = it.second

            when (it.second) {
                is String -> {
                    valorVariable as String
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                is Parcelable -> {
                    valorVariable as Parcelable
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                is Int -> {
                    valorVariable as Int
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                else -> {
                    valorVariable as String
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
            }


        }

        if (codigo != null) {
            startActivityForResult(intentExplicito, codigo)
        } else {
            startActivity(intentExplicito)

        }
    }

    fun cargarProveedor(){
        val listViewRestaurantes = findViewById<ListView>(R.id.ltv_proveedores)
        listViewRestaurantes.adapter = adaptadorProveedores
        listViewRestaurantes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                proveedorSeleccionado = arregloProveedores[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "No seleccionó nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("proveedor")

        referencia
            .get()
            .addOnSuccessListener{
                for (document in it) {
                    Log.i("firebase-firestore", "${document.id} => ${document.data}")
                    var producto = document.toObject(ProveedorDto::class.java)
                    producto.uid = document.id
                    arregloProveedores.add(producto)
                    adaptadorProveedores?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener{ }
    }

    fun cargarProductos(idProveedor: String): String{
        val db = Firebase.firestore
        var uidProducto  = ""

        val referencia = db.collection("producto")

        referencia
            .whereEqualTo("idProv",idProveedor)
            .get()
            .addOnSuccessListener{
                for (document in it) {
                    uidProducto = document.id
                    //Log.i("firebase-firestore","Documento ${document.id}")
                }
            }
            .addOnFailureListener{  Log.i("firebase-firestore","ERROR")}

        return uidProducto
    }

    fun eliminarProveedor(uidProveedor:String){

        val db = Firebase.firestore

        val referenciaProveedor = db.collection("proveedor")

        referenciaProveedor
            .document(uidProveedor)
            .delete()
            .addOnSuccessListener{
                Log.i("Firebase","Se borro Correctamente")
            }
            .addOnFailureListener{
                Log.i("Firebase","ERROR")
            }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //Editar
            R.id.mi_editar ->{
                irActividad(
                    EditarProveedor::class.java, arrayListOf(Pair("Proveedor",ProveedorDto(
                        arregloProveedores.get(posicionItemSeleccionado).uid,
                        arregloProveedores.get(posicionItemSeleccionado).id,
                        arregloProveedores.get(posicionItemSeleccionado).nombre,
                        arregloProveedores.get(posicionItemSeleccionado).cedula,
                        arregloProveedores.get(posicionItemSeleccionado).sueldo,
                        arregloProveedores.get(posicionItemSeleccionado).fecha,
                        arregloProveedores.get(posicionItemSeleccionado).estado)
                    ))
                )
                irActividad(MainActivity::class.java)

                Log.i("bdd", "Editar ${arregloProveedores.get(posicionItemSeleccionado).id}")

                return true
            }

            //Eliminar
            R.id.mi_eliminar ->{

                eliminarProveedor(arregloProveedores.get(posicionItemSeleccionado).uid)
                irActividad(MainActivity::class.java)

                Log.i("bdd", "Eliminar ${arregloProveedores.get(posicionItemSeleccionado).id}")
                return true
            }

            //Productos
            R.id.mi_ver_productos->{
                irActividad(
                    Productos::class.java, arrayListOf(Pair("Proveedor",ProveedorDto(
                        arregloProveedores.get(posicionItemSeleccionado).uid,
                        arregloProveedores.get(posicionItemSeleccionado).id,
                        arregloProveedores.get(posicionItemSeleccionado).nombre,
                        arregloProveedores.get(posicionItemSeleccionado).cedula,
                        arregloProveedores.get(posicionItemSeleccionado).sueldo,
                        arregloProveedores.get(posicionItemSeleccionado).fecha,
                        arregloProveedores.get(posicionItemSeleccionado).estado)
                    ))
                )
                Log.i("bdd", "Ver ${arregloProveedores.get(posicionItemSeleccionado).id} ")
                return true
            }
            else ->  super.onContextItemSelected(item)
        }
    }
}