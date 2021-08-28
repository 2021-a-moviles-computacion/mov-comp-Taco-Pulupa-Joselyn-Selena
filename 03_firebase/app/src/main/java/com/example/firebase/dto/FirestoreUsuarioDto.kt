package com.example.firebase.dto

class FirestoreUsuarioDto(
    var uid: String="",
    var email: String="",
    var roles: ArrayList<String> = arrayListOf()
) {

}