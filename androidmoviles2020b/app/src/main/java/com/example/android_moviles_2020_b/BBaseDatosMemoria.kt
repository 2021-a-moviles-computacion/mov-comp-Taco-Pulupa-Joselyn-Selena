package com.example.android_moviles_2020_b

class BBaseDatosMemoria {

    var papel: String = "Papelito"
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
//        fun arreglarAlgoDelPapel(
//            bddMemoria: BBaseDatosMemoria,
//            nuevoPapel: String
//        ){
//            bddMemoria.papel = nuevoPapel
//        }
        init {
            arregloBEntrenador
                .add(
                    BEntrenador("Adrian","a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Vicente","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Carolina","c@c.com")
                )
        }
    }
}