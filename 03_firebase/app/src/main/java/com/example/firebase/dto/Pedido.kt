package com.example.firebase.dto

class Pedido(
    var producto: String = "",
    var precio: String = "",
    var cantidad: String = "",
    var total: String
) {
    override fun toString(): String {
        return "${producto}  -  ${precio}  -  ${cantidad}  -  ${total}"
    }
}