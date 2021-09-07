package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.firebase.dto.FirestoreProductoDto
import com.example.firebase.dto.FirestoreRestauranteDto
import com.example.firebase.dto.Pedido
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EOrdenes : AppCompatActivity() {
    var arregloRestaurantes = arrayListOf<FirestoreRestauranteDto>()
    var adaptadorRestaurantes: ArrayAdapter<FirestoreRestauranteDto>? = null
    var restauranteSeleccionado: FirestoreRestauranteDto? = null

    var arregloProductos = arrayListOf<FirestoreProductoDto>()
    var adaptadorProductos: ArrayAdapter<FirestoreProductoDto>? = null
    var productoSeleccionado: FirestoreProductoDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)

        if(adaptadorRestaurantes == null) {
            adaptadorRestaurantes = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arregloRestaurantes
            )
            adaptadorRestaurantes?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarRestaurantes()
        }

        if(adaptadorProductos == null) {
            adaptadorProductos = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arregloProductos
            )
            adaptadorProductos?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarProductos()
        }

        var listaProductos = arrayListOf<Pedido>()
        var cantidad = findViewById<EditText>(R.id.et_cantidad_producto)
        var TotalText = findViewById<TextView>(R.id.txv_totalFinal)

        var total :Double = 0.0
        var TotalFinal: Double = 0.0

//        listaProductos.add(Pedido("Piña","0.25","20","5"))
//        TotalFinal = total + 5
//        TotalText.setText(TotalFinal.toString())

        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            listaProductos // arreglo
        )
        val listViewEjemplo = findViewById<ListView>(R.id.lv_lista_productos)
        listViewEjemplo.adapter = adaptador

        val botonAdd = findViewById<Button>(R.id.btn_anadir_lista_producto)
        botonAdd.setOnClickListener {
            total = ((cantidad.text.toString().toInt() * productoSeleccionado!!.precio.toDouble()))
            TotalFinal = total + TotalFinal

            añadirItemsAlListView(
                Pedido(productoSeleccionado!!.nombre,productoSeleccionado!!.precio,cantidad.text.toString(),total.toString()),//////
                listaProductos,
                adaptador
            )
            cantidad.setText("")
            TotalText.setText("")
            TotalText.setText(TotalFinal.toString())

        }

    }


    fun cargarRestaurantes(){
        val spinnerRestaurantes = findViewById<Spinner>(R.id.sp_restaurantes)
        spinnerRestaurantes.adapter = adaptadorRestaurantes
        spinnerRestaurantes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                restauranteSeleccionado = arregloRestaurantes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "No seleccionó nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("restaurante")
            .get()

        referencia
            .addOnSuccessListener{
                for (document in it) {
                    //Log.i("firebase-firestore", "${document.id} => ${document.data}")
                    var restaurante = document.toObject(FirestoreRestauranteDto::class.java)
                    restaurante.uid = document.id
                    arregloRestaurantes.add(restaurante)
                    adaptadorRestaurantes?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener{

            }
    }


    fun cargarProductos(){
        val spinnerProductos = findViewById<Spinner>(R.id.sp_producto)
        spinnerProductos.adapter = adaptadorProductos
        spinnerProductos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                productoSeleccionado = arregloProductos[position]
                Log.i("firebase-firestore", "${productoSeleccionado!!.precio}")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "No seleccionó nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("producto")
            .get()

        referencia
            .addOnSuccessListener{
                for (document in it) {
                    //Log.i("firebase-firestore", "${document.id} => ${document.data}")
                    var producto = document.toObject(FirestoreProductoDto::class.java)
                    producto.uid = document.id
                    arregloProductos.add(producto)
                    adaptadorProductos?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener{

            }
    }


    fun añadirItemsAlListView(
        valor: Pedido,
        arreglo: ArrayList<Pedido>,
        adaptador: ArrayAdapter<Pedido>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()//actualiza la interfaz
    }

}