package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class Productos : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        ProBaseDeDatos.TablaProducto = SqliteProductos(this)

        val idprov = intent.getIntExtra("idProveedor",0)
        val listView = findViewById<ListView>(R.id.ltv_productos)
        val user = ProBaseDeDatos.TablaProducto!!.consultarProductoPorId(idprov)

        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            user // arreglo
        )
        adaptador.notifyDataSetChanged()
        listView.adapter = adaptador

    }

    var posicionItemSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)
        ProBaseDeDatos.TablaProducto = SqliteProductos(this)

        val idprov = intent.getIntExtra("idProveedor",0)
        val nombreProveedor = intent.getStringExtra("nombreProveedor")
        val nombre = findViewById<TextView>(R.id.txv_nombreProveedor)
        nombre.setText("${nombreProveedor}")

        Log.i("bdd", "llegoProducto con idProvedor ${idprov}")

        val botonCrear = findViewById<Button>(
            R.id.btn_crear_producto
        )
        botonCrear.setOnClickListener { abrirActividadProducto(
            CrearProducto::class.java,
            idprov
        )  }
        val listView = findViewById<ListView>(R.id.ltv_productos)

        val user = ProBaseDeDatos.TablaProducto!!.consultarProductoPorId(idprov)

        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            user // arreglo
        )

        listView.adapter = adaptador
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflanter = menuInflater
        inflanter.inflate(R.menu.menuproducto, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
    }

    fun abrirActividadConParametrosProductos(
        clase: Class<*>,
        id: Int,
        nombre: String,
        descripcion: String,
        precio: String,
        total: String,
        fecha: String,
        idProveedor: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra(
            "producto",
            ProductoBDD(id,nombre,descripcion,precio,total,fecha,idProveedor)
        )
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActividadProducto(
        clase: Class<*>,
        idProveedor: Int
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("idProveedor",idProveedor)
        startActivity(intentExplicito)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //Editar
            R.id.mn_editarProducto ->{
                val idprov = intent.getIntExtra("idProveedor",0)
                val user = ProBaseDeDatos.TablaProducto!!.consultarProductoPorId(idprov)
                abrirActividadConParametrosProductos(
                    EditarProducto::class.java,
                    user.get(posicionItemSeleccionado).id,
                    user.get(posicionItemSeleccionado).nombre.toString(),
                    user.get(posicionItemSeleccionado).descripcion.toString(),
                    user.get(posicionItemSeleccionado).precio.toString(),
                    user.get(posicionItemSeleccionado).total.toString(),
                    user.get(posicionItemSeleccionado).fecha.toString(),
                    user.get(posicionItemSeleccionado).idProveedor!!
                )
                onStart()

                Log.i("bdd", "Editar ${ user.get(posicionItemSeleccionado).nombre.toString()}")
                return true
            }

            //Eliminar
            R.id.mn_eliminarProducto ->{
                val idprov = intent.getIntExtra("idProveedor",0)
                val user = ProBaseDeDatos.TablaProducto!!.consultarProductoPorId(idprov)
                ProBaseDeDatos.TablaProducto = SqliteProductos(this)
                if(ProBaseDeDatos.TablaProducto != null){
                    ProBaseDeDatos.TablaProducto!!.eliminarProductoPorId(
                        user.get(posicionItemSeleccionado).id!!.toInt()
                    )
                }
                onStart()
                Log.i("bdd", "Eliminar ${user.get(posicionItemSeleccionado).id}")
                return true
            }

            else ->  super.onContextItemSelected(item)
        }
    }
}