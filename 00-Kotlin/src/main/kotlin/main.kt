import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("hola mundo")
    //Definicion de variable
    var edad = 32
    var edad2: Int = 32// es la manera explicita de definir el tipo de vareable
    // Duck Typing: dentro de kotlin no es necesario definir el tipo de vareable

    var sueldo=1.32
    //edad = 25.5

    //Mutables : son vareables que pueden cambiar o modificarce
    //Inmutables: son vareables que no pueden cambiar o modificarce

    //Variables Mutables (re asignar "=")
    var edadCachorro: Int = 0
    edadCachorro = 1
    edadCachorro = 2
    edadCachorro = 3

    //Variables Inmutables (No re asignables "=")
    val numeroCedula: Int = 1718588104
    //numeroCedula = 123456 ---> No se puede reasignar

    //Tipos de variables (Java): Int, Floar,  etc
    //Primitivos: que no son clases
    val sueldoP: Double = 1.2
    val estadoCivil: Char = 'J'
    val fechaNacimiento: Date = Date()

    ///Clase 05

    //condicionales
    if (true) {

    } else {
    }

    //switch Estado -> s ->c ::

    val estadoCivilWhen: String = "S"
    when (estadoCivilWhen) {
        ("S") -> {   // estas flechitas hace que se ejecute el codigo que esta entre las llaves
            println("Acercarce") // Sirve cuando hay mas de una linea de codigo
        }

        ("C") -> {
            println("Alejarse")
        }

        "UN" -> println("Hablar")   //En este caso no ocupamos lllaves porque solo hay una linea de codigo a ejecitar
        else -> println("No reconocido")
    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO" // es un sentencia de If de codigo de una sola linea

    //condicion ? bloque -true : bloque-false

    imprimirNombre( "Adrian")

    calcularSueldo(100.00)
    calcularSueldo( 100.00, 2.2)
    calcularSueldo( 100.00, 2.2,25.00)

    //Named Parameters: es una forma de enviar parametros en las funciones
    calcularSueldo(
        BonoEspecial = 15.00,
        // tasa = 12 se envia por defecto
        sueldo = 150.00
    )

    calcularSueldo(
        tasa = 14.00,
        BonoEspecial = 150.00,
        sueldo = 100.00
    )

    //Tipos de arreglos
    //Arreglos Estaticos:
    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    //arregloEstatico.add(12) -> No tenemos qui
    // no se puede modificar los elementos del arreglo


    //Arreglos Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES DE LOS ARREGLOS: Sirven para los arreglos estaticos y dinamicos

    //FOR EACH -> Unit
    //Nos ayuda iterar un arreglo como un FOR normal

    val respuestaForEach: Unit = arregloDinamico// dentro de las laves ponemos el codigo
        .forEach{valorActual: Int ->
            println("Valor actual: ${valorActual}")// Con esto ${} no se necesita concatenar las variable
        }
    println(respuestaForEach)

    arregloDinamico// dentro de las laves ponemos el codigo
        .forEach{
            //it:Int -> El calor o los valores que van a llegar a esta funcion
            // Si solamente se recibe 1 parametro, este se va a llamar "it"
            println("Valor actual: ${it}")// Con esto ${} no se necesita concatenar las variable
        }

    arregloDinamico// dentro de las laves ponemos el codigo
        .forEachIndexed { indice: Int,valorActual: Int ->
            println("Valor actual: ${valorActual} Indice: ${indice}")// Con esto ${} no se necesita concatenar las variable
        }
    //println(respuestaForEach)

    //MAP -> Muta el arreglo (Cambia el arreglo)
    //1) Enviamos el nuevo calor de la iteracion
    //2) Nos devuleve es un NUEVO ARREGLO con los valores modificados
    //return@ y el nombre del operador
    //debuelve un arreglo mutable

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            //return@map 10.00
            //return@map valorActual.toDouble()
            return@map valorActual.toDouble()+ 100.00
        }
    println(respuestaMap)

// Para imprimir se lo iguala a una nueva variable
/// Este es la opcion 1
    arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual+15
        }
 // Esta es la opcion 2
        .map { it+15 } //Esta es la linea de reduccion de la opcion 1

//Filter -> FILTRAR EL ARREGLO
//1) Devolver una expresion (TRUE FALSE)
//2) Nuevo arreglo filtrado

    val respuestaFiltere: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual < 5 // Expresion Condicion
            return@filter mayoresACinco
        }
    println(respuestaFiltere)

    // Opcion 1
    val respuestFilter2 = arregloDinamico.filter { it >= 5 }
    println(respuestFilter2)

///////////CLASE 06
    //OR AND
    //OR -> ANY (ALGUNO CUMPLE?)
    //AND -> ALL (TODOS CUMPLEN?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // true

    //REDUCE -> VALOR ACUMULADO
    //1) DEVULVE EL VALOR ACUMULAD =>0
    //2) EN QUE VALOR EMPIEZA =>0
    //[1,2,3,4,5]
    // valorIteracio1 = valorEmpieza +1 -> Iteracion 1
    // 3 = valorIteracion1 + 2 = 1 + 2 = acumulado -> Iteracion 2
    // 6 = valorIteracion3 + 3 = 3 + 3 = acumulado -> Iteracion 3
    // 10 = valorIteracion4 + 4 = 6 + 3 = acumulado -> Iteracion 4
    // 15 = valorIteracion5 + 5 = 10 + 5 = acumulado -> Iteracion 5
    //-> UltimoAcumulado = 15

    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> Siempre empieza en 0
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)//->Logica del negocio
        }
    println(respuestaReduce) // 78

    val arregloDanio = arrayListOf<Int>(12,15,8,10)
    val respuestaReduceFold = arregloDanio
        .fold(100
        ) { acumulado, valorActualAnterior ->
            return@fold (acumulado - valorActualAnterior)
        }
    println(respuestaReduceFold) //55

    //EJERCICIO

    val vidaActual: Double = arregloDinamico
        .map { it * 2.3 } // Arreglo
        .filter { it > 20 } // Arreglo
        .fold(100.00, {acc, i -> acc - i}) // valor
        .also { println(it) } // ejecutar codigo extra
    println("Valor vida actual ${vidaActual}") //3.4

    val ejemploUno = Suma(1,2)

    val ejemploDos = Suma(null, 2)

    val ejemploTres = Suma(1, null)

    println(ejemploUno.sumar())
    println(Suma.historialSuma)
    println(ejemploDos.sumar())
    println(Suma.historialSuma)
    println(ejemploTres.sumar())
    println(Suma.historialSuma)


}//Fin bloque main

//Funcion en java
// void imprimirNombre (String nombre){}
//En Kotlin el unit = void
//Para identar ctrl +  a y ctrol + alt + l
fun imprimirNombre(nombre: String): Unit {
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double,  // Requerido
    tasa: Double = 12.00, // Opcional (defecto)
    //BonoEspecial: Double? // Opcional (null) -> nullable
    BonoEspecial: Double? = null // Opcional (null) -> nullable
): Double {
    if (BonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + BonoEspecial
    }
}

///////////CLASE 06
    //CLASES

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
         uno: Int, //Paramteros requeridos
         dos: Int, //Paramteros requeridos
    ){
        numeroUno = uno
        numeroDos = dos
        println("Inicializar")
    }
}

//instancia.numero uno
//instancia.numero dos
abstract class  Numeros(//Constructor primario
    protected var numeroUno: Int, // propiedad
    protected var numeroDos: Int // propiedad
    ){
    init { // BLoque inicio del constructor
        println("Inicializar")
    }
}

//instancia.numero uno
//instancia.numero dos
class Suma(
    uno: Int, //Paramtro requerido
    dos: Int
): Numeros(//constructor papa (Super)
    uno,
    dos
){
    init {
        this.numeroUno
        this.numeroDos
        // X -> this.uno -> NO EXISTEN
        // X -> this.dos -> NO EXISTEN
    }
    constructor(//Segundo Constructor
    uno: Int?, //parametros
    dos: Int // parametros
    ):this(//llamada constructor primario
        if (uno == null) 0 else uno,
        dos
    )
    constructor(//Tercer Constructor
        uno: Int, //parametros
        dos: Int? // parametros
    ):this(//llamada constructor primario
        uno,
        if(dos == null) 0 else dos
    )

    //no es necesario poner la palabra public ya que todos los metodos
    // y propiedades son publicos
    //public fun sumar():Int{
    fun sumar():Int{
        //val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos
        agregarHiatiorial(total)
        return total
    }

    //SINGLETION
    // no se van a isntanciar lo que se ponga dentro del parentesis
    companion object {
        val  historialSuma = arrayListOf<Int>()

        fun agregarHiatiorial(valorNuevaSuma: Int){
            //this.historialSuma.add(valorNuevaSuma)
            historialSuma.add(valorNuevaSuma)
            println(historialSuma)
        }
    }
}
