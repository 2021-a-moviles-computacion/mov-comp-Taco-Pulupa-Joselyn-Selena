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

class Productos : AppCompatActivity() {
    var posicionItemSeleccionado = 0

    var arregloProducto = arrayListOf<ProductoDto>()
    var adaptadorProducto: ArrayAdapter<ProductoDto>? = null
    var productoSeleccionado: ProductoDto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val Proveedor = intent.getParcelableExtra<ProveedorDto>("Proveedor")
        val nombre = findViewById<TextView>(R.id.txv_nombreProveedor)
        nombre.setText("${Proveedor?.nombre}")

        val listViewRestaurantes = findViewById<ListView>(R.id.ltv_productos)

        if(adaptadorProducto == null) {
            adaptadorProducto = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                arregloProducto
            )
            adaptadorProducto?.setDropDownViewResource(android.R.layout.simple_list_item_1)
            cargarProducto()
        }

        val botonCrear = findViewById<Button>(R.id.btn_crear_producto)
        botonCrear.setOnClickListener { irActividad(
            CrearProducto::class.java, arrayListOf(Pair("Proveedor", Proveedor?.id))
        )  }

        registerForContextMenu(listViewRestaurantes)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflanter = menuInflater
        inflanter.inflate(R.menu.menu_producto, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
    }

    fun eliminarProducto(uidProducto:String){

        val db = Firebase.firestore

        val referenciaProveedor = db.collection("producto")

        referenciaProveedor
            .document(uidProducto)
            .delete()
            .addOnSuccessListener{
                Log.i("Firebase","Se borro Correctamente")
            }
            .addOnFailureListener{
                Log.i("Firebase","ERROR")
            }

    }

    fun cargarProducto(){
        val Proveedor = intent.getParcelableExtra<ProveedorDto>("Proveedor")
        val listViewRestaurantes = findViewById<ListView>(R.id.ltv_productos)
        listViewRestaurantes.adapter = adaptadorProducto
        listViewRestaurantes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                productoSeleccionado = arregloProducto[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "No seleccionó nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("producto")

        referencia
            .whereEqualTo("idProv",Proveedor?.id)
            .get()
            .addOnSuccessListener{
                for (document in it) {
                    var producto = document.toObject(ProductoDto::class.java)
                    producto.uid = document.id
                    arregloProducto.add(producto)
                    adaptadorProducto?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener{  Log.i("firebase-firestore","ERROR")}
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

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //Editar
            R.id.mn_editarProducto ->{
                irActividad(
                    EditarProducto::class.java, arrayListOf(Pair("Producto",ProductoDto(
                        arregloProducto.get(posicionItemSeleccionado).uid,
                        arregloProducto.get(posicionItemSeleccionado).id,
                        arregloProducto.get(posicionItemSeleccionado).nombre,
                        arregloProducto.get(posicionItemSeleccionado).descripcion,
                        arregloProducto.get(posicionItemSeleccionado).precio,
                        arregloProducto.get(posicionItemSeleccionado).total,
                        arregloProducto.get(posicionItemSeleccionado).fecha,
                        arregloProducto.get(posicionItemSeleccionado).idProveedor,
                        arregloProducto.get(posicionItemSeleccionado).latitudText,
                        arregloProducto.get(posicionItemSeleccionado).longitudText)
                    ))
                )
                irActividad(Productos::class.java)

                Log.i("bdd", "Editar ${arregloProducto.get(posicionItemSeleccionado).latitudText}")
                return true
            }

            //Eliminar
            R.id.mn_eliminarProducto ->{

                eliminarProducto(arregloProducto.get(posicionItemSeleccionado).uid)
                irActividad(Productos::class.java)

                Log.i("bdd", "Eliminar ${arregloProducto.get(posicionItemSeleccionado).id}")
                return true
            }

            R.id.mn_localizacion ->{
                irActividad(
                    Mapa::class.java, arrayListOf(Pair("Producto",ProductoDto(
                        arregloProducto.get(posicionItemSeleccionado).uid,
                        arregloProducto.get(posicionItemSeleccionado).id,
                        arregloProducto.get(posicionItemSeleccionado).nombre,
                        arregloProducto.get(posicionItemSeleccionado).descripcion,
                        arregloProducto.get(posicionItemSeleccionado).precio,
                        arregloProducto.get(posicionItemSeleccionado).total,
                        arregloProducto.get(posicionItemSeleccionado).fecha,
                        arregloProducto.get(posicionItemSeleccionado).idProveedor,
                        arregloProducto.get(posicionItemSeleccionado).latitudText,
                        arregloProducto.get(posicionItemSeleccionado).longitudText)
                    ))
                )

                Log.i("bdd", "Lolalizar ${arregloProducto.get(posicionItemSeleccionado).longitudText}")
                return true
            }

            else ->  super.onContextItemSelected(item)
        }
    }

}