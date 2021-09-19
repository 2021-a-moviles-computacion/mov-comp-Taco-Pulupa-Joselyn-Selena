package com.example.examen_02.dto

import android.os.Parcel
import android.os.Parcelable

class ProveedorDto(
    var uid:String = "",
    var id:String = "",
    var nombre: String = "",
    var cedula: String = "",
    var sueldo: String = "",
    var fecha: String = "",
    var estado: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
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
        return "${nombre} - ${cedula} - ${sueldo}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeString(cedula)
        parcel.writeString(sueldo)
        parcel.writeString(fecha)
        parcel.writeString(estado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProveedorDto> {
        override fun createFromParcel(parcel: Parcel): ProveedorDto {
            return ProveedorDto(parcel)
        }

        override fun newArray(size: Int): Array<ProveedorDto?> {
            return arrayOfNulls(size)
        }
    }
}