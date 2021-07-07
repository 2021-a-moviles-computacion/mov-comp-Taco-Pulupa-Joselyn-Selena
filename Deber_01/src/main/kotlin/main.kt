import java.io.*
import javax.swing.JOptionPane
import kotlin.collections.ArrayList


fun main() {

    val PathProveedor = "resources/proveedores.txt"
    val PathProducto = "resources/productos.txt"

    menu(PathProveedor,PathProducto)
}

fun menu(ProvP:String, ProdP:String) {

    val menu = "Seleccione el modulo al que desea ingresar:\n" +
            "1. Proveedor\n" +
            "2. Producto\n"+
            "3. Salir"
    val menuSecundario = "Seleccione la accion que desea:\n" +
            "1. Ingresar un nuevo dato\n" +
            "2. Mostrar datos\n" +
            "3. Actualizar\n" +
            "4. Eliminar\n" +
            "5. Regresar"
    val menuActualizarProveedor = "Seleccione el dato:\n" +
            "1. Nombre\n" +
            "2. Sueldo\n" +
            "3. Estado\n"+
            "4. Regresar"
    val menuActualizarProducto= "Seleccione el dato:\n" +
            "1. Nombre del Producto\n" +
            "2. Precio\n" +
            "3. Fecha de Vencimiento\n"+
            "4. Total en Stock\n"+
            "5. Nombre Proveedor\n"+
            "6. Regresar"


    var opcionMenu = JOptionPane.showInputDialog(null,menu)
    val Proveedor = Proveedor()
    val Producto = Producto()

    if (opcionMenu.matches("[0-9]*".toRegex())) {

        if (opcionMenu.toInt() in 1 until 4) {

            when (opcionMenu.toInt()) {
                1 -> {//Proveedor
                    opcionMenu = JOptionPane.showInputDialog(null, menuSecundario)

                    if (opcionMenu.matches("[0-9]*".toRegex())) {

                        if (opcionMenu.toInt() in 1 until 6) {
                            when (opcionMenu.toInt()) {
                                1 -> {//Registrar
                                    escribirNuenaLineaArchivo(Proveedor.nuevoProveedor(), ProvP)
                                    JOptionPane.showMessageDialog(null, "Operación realizada correctamente")
                                    menu(ProvP, ProdP)
                                }
                                2 -> {//Mostrar
                                    println("Nombre,Cedula,Sueldo,Fecha de Registro,Estado")
                                    leerArchivo(ProvP)
                                    menu(ProvP, ProdP)
                                }
                                3 -> {//Actualizar
                                    opcionMenu = JOptionPane.showInputDialog(null, menuActualizarProveedor)

                                    if (opcionMenu.matches("[0-9]*".toRegex())) {

                                        var numCed = JOptionPane.showInputDialog(
                                                null,
                                                "Ingrese el numero de cedula del proveedor"
                                            )
                                        if (buscarDato(ProvP, numCed)) {
                                            if (opcionMenu.toInt() in 1 until 5) {
                                                if (opcionMenu.toInt() != 4) {
                                                    reescribirArchivo(
                                                        Proveedor.ActualizarProvedor(
                                                            ProvP,
                                                            opcionMenu.toInt(),
                                                            numCed
                                                        ), ProvP
                                                    )
                                                    JOptionPane.showMessageDialog(
                                                        null,
                                                        "Operación realizada correctamente"
                                                    )
                                                    menu(ProvP, ProdP)
                                                } else menu(ProvP, ProdP)
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(
                                                null,
                                                "Proveedor no encontrado",
                                                "Error!",
                                                JOptionPane.ERROR_MESSAGE
                                            )
                                            menu(ProvP, ProdP)
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Ingrese solo numeros", "Error!", JOptionPane.ERROR_MESSAGE)
                                        menu(ProvP, ProdP)
                                    }
                                }
                                4 -> {//Eliminar
                                    var numCed = JOptionPane.showInputDialog(null, "Ingrese el numero de cedula del proveedor")

                                    if (buscarDato(ProvP, numCed)) {
                                        val resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
                                        if (resp == 0) {
                                            reescribirArchivo(Proveedor.eliminarProveedor(ProvP, numCed), ProvP)
                                            JOptionPane.showMessageDialog(null, "Operación realizada correctamente")
                                            menu(ProvP, ProdP)
                                        } else menu(ProvP, ProdP)
                                    } else {
                                        JOptionPane.showMessageDialog(
                                            null,
                                            "Proveedor no encontrado",
                                            "Error!",
                                            JOptionPane.ERROR_MESSAGE
                                        )
                                        menu(ProvP, ProdP)
                                    }
                                }
                                5 -> {//Regresar
                                    menu(ProvP, ProdP)
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Ingrese un numero entre 1 y 3",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE
                            )
                            menu(ProvP, ProdP)
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ingrese solo numeros", "Error!", JOptionPane.ERROR_MESSAGE)
                        menu(ProvP, ProdP)
                    }
                }
                2 -> {//Producto
                    opcionMenu = JOptionPane.showInputDialog(null, menuSecundario)

                    if (opcionMenu.matches("[0-9]*".toRegex())) {

                        if (opcionMenu.toInt() in 1 until 6) {
                            when (opcionMenu.toInt()) {
                                1 -> {//Registrar
                                    leerArchivo(ProvP)
                                    escribirNuenaLineaArchivo(Producto.nuevoProducto(), ProdP)
                                    JOptionPane.showMessageDialog(null, "Operación realizada correctamente")
                                    menu(ProvP, ProdP)
                                }
                                2 -> {//Mostrar
                                    println("Producto,Precio,Fecha Vencimiento,Total,Nombre Proveedor,Descripcion")
                                    leerArchivo(ProdP)
                                    menu(ProvP, ProdP)
                                }
                                3 -> {//Actualizar
                                    opcionMenu = JOptionPane.showInputDialog(null, menuActualizarProducto)

                                    if (opcionMenu.matches("[0-9]*".toRegex())) {

                                        var nomProd =
                                            JOptionPane.showInputDialog(null, "Ingrese el nombre del Producto")
                                        if (buscarDato(ProdP, nomProd)) {
                                            if (opcionMenu.toInt() in 1 until 7) {
                                                if (opcionMenu.toInt() != 6) {
                                                    reescribirArchivo(
                                                        Producto.ActualizarProducto(
                                                            ProdP,
                                                            opcionMenu.toInt() - 1,
                                                            nomProd
                                                        ), ProdP
                                                    )
                                                    JOptionPane.showMessageDialog(
                                                        null,
                                                        "Operación realizada correctamente"
                                                    )
                                                    menu(ProvP, ProdP)
                                                } else menu(ProvP, ProdP)
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(
                                                null,
                                                "Producto no encontrado",
                                                "Error!",
                                                JOptionPane.ERROR_MESSAGE
                                            )
                                            menu(ProvP, ProdP)
                                        }
                                    }else {
                                        JOptionPane.showMessageDialog(null, "Ingrese solo numeros", "Error!", JOptionPane.ERROR_MESSAGE)
                                        menu(ProvP, ProdP)
                                    }
                                }
                                4 -> {//Borrar
                                    var nomProd =
                                        JOptionPane.showInputDialog(null, "Ingrese el numero de cedula del proveedor")

                                    if (buscarDato(ProdP, nomProd)) {
                                        val resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
                                        if (resp == 0) {
                                            reescribirArchivo(Producto.eliminarProducto(ProdP, nomProd), ProdP)
                                            JOptionPane.showMessageDialog(null, "Operación realizada correctamente")
                                            menu(ProvP, ProdP)
                                        } else menu(ProvP, ProdP)
                                    } else {
                                        JOptionPane.showMessageDialog(
                                            null,
                                            "Producto no encontrado",
                                            "Error!",
                                            JOptionPane.ERROR_MESSAGE
                                        )
                                        menu(ProvP, ProdP)
                                    }
                                }
                                5 -> {//Regresar
                                    menu(ProvP, ProdP)
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Ingrese un numero entre 1 y 4",
                                "Error!",
                                JOptionPane.ERROR_MESSAGE
                            )
                            menu(ProvP, ProdP)
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Ingrese solo numeros", "Error!", JOptionPane.ERROR_MESSAGE)
                        menu(ProvP, ProdP)
                    }
                }
                3 -> {//Salir
                    JOptionPane.showMessageDialog(null, "Salir del Sistema")
                    System.exit(0)
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un numero entre 1 y 2", "Error!", JOptionPane.ERROR_MESSAGE)
            menu(ProvP, ProdP)
        }
    }else {
        JOptionPane.showMessageDialog(null, "Ingrese solo numeros", "Error!", JOptionPane.ERROR_MESSAGE)
        menu(ProvP, ProdP)
    }
}

fun escribirNuenaLineaArchivo(text: String, path : String){

    val archivo = File(path)
    archivo.appendText(text)
}

fun leerArchivo(path: String){

    val archivo = File(path)
    archivo.forEachLine { print(it+"\n") }

}
fun reescribirArchivo(registro: ArrayList<MutableList<String>>,path: String) {
    val writer = PrintWriter(path)
    registro.forEach { lineList ->
        lineList.forEach {
            if (it == lineList[lineList.size - 1]) writer.append("$it\n") else writer.append("$it,")
        }
    }
    writer.close()
}

fun buscarDato(path: String,dato: String): Boolean {
    val archivo= File(path)
    val lineList = java.util.ArrayList<MutableList<String>>()

    archivo.forEachLine {
        lineList.add(it.split(",") as MutableList<String>)
    }
    val list = lineList
    return list.any { it[0].equals(dato, ignoreCase = true) }
}
