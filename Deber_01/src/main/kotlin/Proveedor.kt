import java.io.File
import java.util.*
import javax.swing.JOptionPane

class Proveedor () {
    var nombre: String = ""
    var cedula: String = ""
    var sueldo: Double = 0.0
    var fechaRegistro: Date = Date()
    var estado: Boolean = false

    fun nuevoProveedor(): String {

        val separador = ","
        val salto = "\n"
        nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre")
        cedula = JOptionPane.showInputDialog(null,"Ingrese el cedula")
        sueldo = JOptionPane.showInputDialog(null,"Ingrese el sueldo").toDouble()
        fechaRegistro = Date()
        var estadoR = JOptionPane.showInputDialog(null,"Ingrese el estado del Proveedor:\n Activo = 1\n Pasivo = 2")
        if (estadoR == "2"){
            estado = true
        }
        return "$cedula$separador$nombre$separador$sueldo$separador$estado$separador$fechaRegistro$salto"
    }

    fun ActualizarProvedor(path: String, opcion:Int, numC:String): ArrayList<MutableList<String>> {

        val archivo= File(path)
        val lineList = ArrayList<MutableList<String>>()
        var cambio:String = ""

        archivo.forEachLine {
            lineList.add(it.split(",") as MutableList<String>)
        }

        var nuevoList = lineList
        nuevoList.forEach {
            if (it[0].equals(numC, true)){
                cambio = JOptionPane.showInputDialog(null,"Ingrese el nuevo dato",it[opcion])
                it[opcion] = cambio
            }
        }

        return nuevoList
    }

    fun eliminarProveedor(path: String,numC: String): ArrayList<MutableList<String>> {
        val archivo= File(path)
        val lineList = ArrayList<MutableList<String>>()

        archivo.forEachLine {
            lineList.add(it.split(",") as MutableList<String>)
        }

        var nuevoList = lineList
        nuevoList = nuevoList.filter { !(it[0].equals(numC, true)) } as ArrayList<MutableList<String>>
        return nuevoList
    }

}