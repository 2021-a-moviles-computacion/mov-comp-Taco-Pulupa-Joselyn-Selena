package com.example.examen

class ProBaseDatos() {

    companion object{
        val arregloProducto = arrayListOf<ProductoBDD>()
        init {
            if(ProBaseDeDatos.TablaProducto != null){
                val user = ProBaseDeDatos.TablaProducto!!.consultarPRoductos()
                user.forEachIndexed { index:Int, s ->
                    arregloProducto.add(ProductoBDD(user.get(index).id,user.get(index).nombre,
                        user.get(index).descripcion,user.get(index).precio,user.get(index).total,
                        user.get(index).fecha,user.get(index).idProveedor))
                }
            }
        }
    }
}