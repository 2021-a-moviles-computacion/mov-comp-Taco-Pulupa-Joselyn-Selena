package com.example.examen

class PPBaseDatos {
    companion object{
        val arregloProveedor = arrayListOf<ProveedorBDD>()
        init {
            if(EBaseDeDatos.TablaProveedor != null){
            val user = EBaseDeDatos.TablaProveedor!!.consultarListaUsuario()
            user.forEachIndexed { index:Int, s ->
                arregloProveedor.add(ProveedorBDD(user.get(index).id,user.get(index).nombre,
                    user.get(index).cedula,user.get(index).sueldo,user.get(index).fecha,user.get(index).estado))
            }
            }

        }

    }
}
