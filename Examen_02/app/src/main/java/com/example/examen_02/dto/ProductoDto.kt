package com.example.examen_02.dto

import android.os.Parcel
import android.os.Parcelable

class ProductoDto (
    var uid:String = "",
    var id: String = "",
    var nombre: String = "",
    var descripcion: String = "",
    var precio: String = "",
    var total: String = "",
    var fecha: String = "",
    var idProveedor: String = "",
    var latitudText: String = "",
    var longitudText: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun toString(): String{
        return "${nombre} - ${precio} - ${total}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeString(precio)
        parcel.writeString(total)
        parcel.writeString(fecha)
        parcel.writeString(idProveedor)
        parcel.writeString(latitudText)
        parcel.writeString(longitudText)
    }

    companion object CREATOR : Parcelable.Creator<ProductoDto> {
        override fun createFromParcel(parcel: Parcel): ProductoDto {
            return ProductoDto(parcel)
        }

        override fun newArray(size: Int): Array<ProductoDto?> {
            return arrayOfNulls(size)
        }
    }
}