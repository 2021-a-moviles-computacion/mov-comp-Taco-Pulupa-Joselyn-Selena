package com.example.examen

import android.content.Intent
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

class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        EBaseDeDatos.TablaProveedor = SqliteProveedor(this)
        val proveedores =EBaseDeDatos.TablaProveedor!!.consultarListaUsuario()
        val listView = findViewById<ListView>(R.id.ltv_proveedores)

        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            proveedores// arreglo
        )
        adaptador.notifyDataSetChanged()
        listView.adapter = adaptador
    }

    var posicionItemSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EBaseDeDatos.TablaProveedor = SqliteProveedor(this)
        Log.i("bdd", EBaseDeDatos.TablaProveedor!!.databaseName.toString())

       val proveedores = PPBaseDatos.arregloProveedor
        val listView = findViewById<ListView>(R.id.ltv_proveedores)

        val adaptador = ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //layout (visual)
            proveedores// arreglo
        )
        adaptador.notifyDataSetChanged()
        listView.adapter = adaptador

        val botonCrear = findViewById<Button>(
            R.id.btn_crear
        )
        botonCrear.setOnClickListener { abrirActividad(CrearProveedor::class.java) }

        registerForContextMenu(listView)

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
    }

    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }

    fun abrirActividadProducto(
        clase: Class<*>,
        idProveedor: Int,
        nombreProveedor: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("idProveedor",idProveedor)
        intentExplicito.putExtra("nombreProveedor",nombreProveedor)
        startActivity(intentExplicito)
    }

    fun abrirActividadConParametros(
        clase: Class<*>,
        id: Int,
        nombre: String,
        cedula: String,
        sueldo: String,
        fecha: String,
        estado: String
    ){
        val intentExplicito = Intent(
            this,
            clase
        )

        intentExplicito.putExtra(
            "proveedor",
            ProveedorBDD(id,nombre,cedula,sueldo,fecha,estado)
        )
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            //Editar
            R.id.mi_editar ->{
                abrirActividadConParametros(
                    EditarProveedor::class.java,
                    PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).id,
                    PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).nombre.toString(),
                    PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).cedula.toString(),
                    PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).sueldo.toString(),
                    PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).fecha.toString(),
                    PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).estado.toString()
                )

                onStart()

                Log.i("bdd", "Editar ${PPBaseDatos.arregloProveedor[
                        posicionItemSeleccionado
                ]}")
                return true
            }

            //Eliminar
            R.id.mi_eliminar ->{

                ProBaseDeDatos.TablaProducto = SqliteProductos(this)
                if(ProBaseDeDatos.TablaProducto != null){
                    ProBaseDeDatos.TablaProducto!!.eliminarProductoPorId(
                        PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).id!!.toInt()
                    )
                }

                EBaseDeDatos.TablaProveedor = SqliteProveedor(this)
                if(EBaseDeDatos.TablaProveedor != null){
                    EBaseDeDatos.TablaProveedor!!.eliminarUsuarioPorId(
                        PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).id!!.toInt()
                    )
                }
                onStart()

                Log.i("bdd", "Eliminar ${PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).id}")
                return true
            }

            //Productos
            R.id.mi_ver_productos->{
                abrirActividadProducto(
                    Productos::class.java,
                    PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).id,
                    PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).nombre.toString()
                )
                Log.i("bdd", "Ver ${PPBaseDatos.arregloProveedor[
                        posicionItemSeleccionado
                ]} y id ${PPBaseDatos.arregloProveedor.get(posicionItemSeleccionado).id} ")
                return true
            }
            else ->  super.onContextItemSelected(item)
        }
    }
}