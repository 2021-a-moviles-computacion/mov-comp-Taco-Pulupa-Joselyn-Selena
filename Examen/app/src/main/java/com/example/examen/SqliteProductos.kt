package com.example.examen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteProductos (
    context: Context?,
) : SQLiteOpenHelper(
    context,
    "movilesProd",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                create table PRODUCTO(
                id integer primary key autoincrement,
                nombre varchar(50),
                descripcion varchar(50),
                precio varchar(5),  
                total varchar(5),
                fecha varchar(10),
                idProveedor varchar(5)
                )
            """.trimIndent()
        Log.i("bdd","Creando la tabla de PRODUCTO")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearProducto(
        nombre: String,
        descripcion: String,
        precio: String,
        total: String,
        fecha: String,
        idProveedor: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        valoresAGuardar.put("precio", precio)
        valoresAGuardar.put("total", total)
        valoresAGuardar.put("fecha", fecha)
        valoresAGuardar.put("idProveedor", idProveedor)
        Log.i("bdd","llego crear producto ${idProveedor} y ${nombre}")

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "PRODUCTO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return  if (resultadoEscritura.toInt() == 1) false else true
    }

    fun consultarPRoductos(): ArrayList<ProductoBDD> {
        val scriptConsultarUsuario = "SELECT * FROM PRODUCTO"

        val baseDatosLEctura = readableDatabase
        val resultaConsultaLectura = baseDatosLEctura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val arregloUsuario = ArrayList<ProductoBDD>() // si esperamos mas de una respuesta

        if(existeUsuario){
            resultaConsultaLectura.moveToFirst()
            while (!resultaConsultaLectura.isAfterLast) {
                val productoEncontrado = ProductoBDD(0,"","","","","","")

                val id = resultaConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) //Columna indice 1 -> ID
                val descripcion = resultaConsultaLectura.getString(2)
                val precio = resultaConsultaLectura.getString(3)
                val total = resultaConsultaLectura.getString(4)//Columna indice 2 -> ID
                val fecha = resultaConsultaLectura.getString(5)
                val idProveedor = resultaConsultaLectura.getString(6)

                //if (id != null){
                    productoEncontrado.id = id
                    productoEncontrado.nombre = nombre
                    productoEncontrado.descripcion = descripcion
                    productoEncontrado.precio = precio
                    productoEncontrado.total = total
                    productoEncontrado.fecha = fecha
                    productoEncontrado.idProveedor = idProveedor
                    arregloUsuario.add(productoEncontrado)
                    resultaConsultaLectura.moveToNext()
                //}
            }
        }
        resultaConsultaLectura.close()//Cerrar siempre las consultas
        baseDatosLEctura.close()
        return arregloUsuario

    }

    fun consultarProductoPorId(id : Int): ArrayList<ProductoBDD> {
        Log.i("bdd","Ingreso a consultar PRODUCTO")
        val scriptConsultarUsuario = "SELECT * FROM PRODUCTO where idProveedor = ${id}"

        val baseDatosLEctura = readableDatabase
        val resultaConsultaLectura = baseDatosLEctura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
        val arregloUsuario = ArrayList<ProductoBDD>() // si esperamos mas de una respuesta

        if(existeUsuario){
            resultaConsultaLectura.moveToFirst()
            while (!resultaConsultaLectura.isAfterLast) {
                val productoEncontrado = ProductoBDD(0,"","","","","","")

                val id = resultaConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) //Columna indice 1 -> ID
                val descripcion = resultaConsultaLectura.getString(2)
                val precio = resultaConsultaLectura.getString(3)
                val total = resultaConsultaLectura.getString(4)//Columna indice 2 -> ID
                val fecha = resultaConsultaLectura.getString(5)
                val idProveedor = resultaConsultaLectura.getString(6)

                productoEncontrado.id = id
                productoEncontrado.nombre = nombre
                productoEncontrado.descripcion = descripcion
                productoEncontrado.precio = precio
                productoEncontrado.total = total
                productoEncontrado.fecha = fecha
                productoEncontrado.idProveedor = idProveedor
                arregloUsuario.add(productoEncontrado)
                resultaConsultaLectura.moveToNext()
                Log.i("bdd","${productoEncontrado.nombre} y ${productoEncontrado.precio}")
            }
        }

        resultaConsultaLectura.close()//Cerrar siempre las consultas
        baseDatosLEctura.close()
        return arregloUsuario

    }

    fun eliminarProductoPorId(id:Int):Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete("PRODUCTO",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarProductoFormulario(
        nombre: String,
        descripcion: String,
        precio: String,
        total: String,
        fecha: String,
        idProducto: String
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre",nombre)
        valoresActualizar.put("descripcion",descripcion)
        valoresActualizar.put("precio",precio)
        valoresActualizar.put("total",total)
        valoresActualizar.put("fecha",fecha)
        valoresActualizar.put("id",idProducto)

        Log.i("bdd","Ingreso a actualizar PRODUCTO con precio ${precio}")
        val resultadoActualizacion = conexionEscritura
            .update(
                "PRODUCTO", // Nombre Tabla
                valoresActualizar,//Valores a actualizar
                "id=?",//Clausula Where
                arrayOf(
                    idProducto.toString()
                ) //Parametros consultar Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

}