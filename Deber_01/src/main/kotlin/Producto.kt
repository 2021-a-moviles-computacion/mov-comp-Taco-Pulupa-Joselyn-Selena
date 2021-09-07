import java.io.File
import java.util.ArrayList
import javax.swing.JOptionPane

class Producto {
    var name: String = ""
    var descripcion: String = ""
    var nameProveedor: String = ""
    var precio: Double = 0.0
    var totalProductoDisponible: Int = 0
    var fechaVencimiento: String = ""

    fun nuevoProducto(): String {

        val separador = ","
        val salto = "\n"
        name = JOptionPane.showInputDialog(null,"Ingrese el nombre del producto")
        precio = JOptionPane.showInputDialog(null,"Ingrese el precio del producto").toDouble()
        totalProductoDisponible = JOptionPane.showInputDialog(null,"Ingrese el numero total de productos disponibles").toInt()
        fechaVencimiento = JOptionPane.showInputDialog(null,"Ingrese la fecha de vencimiento del producto\n Formato: aa/mm/dd")
        descripcion = JOptionPane.showInputDialog(null,"Ingrese una descripción del producto")
        nameProveedor = JOptionPane.showInputDialog(null,"Ingrese el nombre del Proveedor")
        return "$name$separador$precio$separador$fechaVencimiento$separador$totalProductoDisponible$separador$nameProveedor$separador$descripcion$salto"
    }

    fun ActualizarProducto(path: String, opcion:Int, nomPr:String): ArrayList<MutableList<String>> {

        val archivo= File(path)
        val lineList = ArrayList<MutableList<String>>()
        var cambio:String = ""

        archivo.forEachLine {
            lineList.add(it.split(",") as MutableList<String>)
        }

        var nuevoList = lineList
        nuevoList.forEach {
            if (it[0].equals(nomPr, true)){
                cambio = JOptionPane.showInputDialog(null,"Ingrese el nuevo dato",it[opcion])
                it[opcion] = cambio
            }
        }

        return nuevoList
    }

    fun eliminarProducto(path: String, nomPr: String): ArrayList<MutableList<String>> {
        val archivo= File(path)
        val lineList = ArrayList<MutableList<String>>()

        archivo.forEachLine {
            lineList.add(it.split(",") as MutableList<String>)
        }

        var nuevoList = lineList
        nuevoList = nuevoList.filter { !(it[0].equals(nomPr, true)) } as ArrayList<MutableList<String>>
        return nuevoList
    }
}