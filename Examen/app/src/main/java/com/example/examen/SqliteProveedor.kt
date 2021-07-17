package com.example.examen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import kotlin.collections.ArrayList

class SqliteProveedor (
    context: Context?,
) : SQLiteOpenHelper(
    context,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                create table PROVEEDOR(
                id integer primary key autoincrement,
                nombre varchar(50),
                cedula varchar(10),
                sueldo varchar(5),
                fecha varchar(10),
                estado varchar(1)
                )
            """.trimIndent()
        Log.i("bdd","Creando la tabla de PROVEEDOR")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearUsuarioFormulario(
        nombre: String,
        cedula: String,
        sueldo: String,
        fecha: String,
        estado: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("cedula", cedula)
        valoresAGuardar.put("sueldo", sueldo)
        valoresAGuardar.put("fecha", fecha)
        valoresAGuardar.put("estado", estado)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "PROVEEDOR",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return  if (resultadoEscritura.toInt() == 1) false else true
    }


    fun consultarListaUsuario(): ArrayList<ProveedorBDD> {
        Log.i("bdd","Ingresa consultar Proveedores")
        val scriptConsultarUsuario = "SELECT * FROM PROVEEDOR"

        val baseDatosLEctura = readableDatabase
        val resultaConsultaLectura = baseDatosLEctura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
         val arregloPRoveedor= ArrayList<ProveedorBDD>() // si esperamos mas de una respuesta

        if(existeUsuario){
            resultaConsultaLectura.moveToFirst()
            while (!resultaConsultaLectura.isAfterLast) {
                val usuarioEncontrado = ProveedorBDD(0,"","","","","")

                val id = resultaConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) //Columna indice 1 -> ID
                val cedula = resultaConsultaLectura.getString(2)
                val sueldo = resultaConsultaLectura.getString(3)
                val fecha = resultaConsultaLectura.getString(4)//Columna indice 2 -> ID
                val estado = resultaConsultaLectura.getString(5)

//                if (id != null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.cedula = cedula
                    usuarioEncontrado.sueldo = sueldo
                    usuarioEncontrado.fecha = fecha
                    usuarioEncontrado.estado = estado
                    arregloPRoveedor.add(usuarioEncontrado)
                    resultaConsultaLectura.moveToNext()
                    Log.i("bdd","${usuarioEncontrado.nombre} y ${usuarioEncontrado.id}")
                //}
            }
        }

        resultaConsultaLectura.close()//Cerrar siempre las consultas
        baseDatosLEctura.close()
        return arregloPRoveedor

    }

    fun eliminarUsuarioPorId(id:Int):Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete("PROVEEDOR",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarUsuaruiFormulario(
        nombre: String,
        cedula: String,
        sueldo: String,
        fecha: String,
        estado: String,
        idActualizar: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre",nombre)
        valoresActualizar.put("cedula",cedula)
        valoresActualizar.put("sueldo",sueldo)
        valoresActualizar.put("fecha",fecha)
        valoresActualizar.put("estado",estado)
        val resultadoActualizacion = conexionEscritura
            .update(
                "PROVEEDOR", // Nombre Tabla
                valoresActualizar,//Valores a actualizar
                "id=?",//Clausula Where
                arrayOf(
                    idActualizar.toString()
                ) //Parametros consultar Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

}