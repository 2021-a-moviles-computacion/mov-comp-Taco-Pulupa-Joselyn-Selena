package com.example.android_moviles_2020_b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(
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
                CREATE TABLE USUARIO (
                id INTERGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion varchar(50)
                )
            """.trimIndent()
        Log.i("bbd","Creando la tabla de usuario")
        db?.execSQL(scriptCrearTablaUsuario)

    }

    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        val resultadoEscritura: Long = conexionEscritura
            .insert(
                "USUARIO",
                null,
                valoresAGuardar
            )
        conexionEscritura.close()
        return  if (resultadoEscritura.toInt() == 1) false else true
    }

    fun consultarUsuarioPorId(id : Int): EUsuarioBDD{
        val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE ID = ${id}"

        val baseDatosLEctura = readableDatabase
        val resultaConsultaLectura = baseDatosLEctura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultaConsultaLectura.moveToFirst()
       // val arregloUsuario = arrayListOf<EUsuarioBDD>() // si esperamos mas de una respuesta
        val usuarioEncontrado = EUsuarioBDD(0,"","")
        if(existeUsuario){
            do {
                val id = resultaConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val nombre = resultaConsultaLectura.getString(1) //Columna indice 1 -> ID
                val descripcion = resultaConsultaLectura.getString(2) //Columna indice 2 -> ID

                if (id != null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    //arregloUsuario.add(usuarioEncontrado)
                }

            }while (resultaConsultaLectura.moveToNext())
        }
        resultaConsultaLectura.close()//Cerrar siempre las consultas
        baseDatosLEctura.close()
        return usuarioEncontrado

    }

    fun eliminarUsuarioPorId(id:Int):Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete("USUARIO",
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
        descripcion: String,
        idActualizar: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre",nombre)
        valoresActualizar.put("descripcion",descripcion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "USUARIO", // Nombre Tabla
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