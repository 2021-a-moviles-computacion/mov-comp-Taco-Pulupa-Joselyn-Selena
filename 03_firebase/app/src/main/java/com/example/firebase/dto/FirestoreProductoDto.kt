package com.example.firebase.dto

data class FirestoreProductoDto(
    var uid:String = "",
    var nombre:String = "",
    var precio: String = ""
) {

    override fun toString():String{
        return "${nombre} - ${precio}"
    }

}