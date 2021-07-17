package com.example.examen

import android.os.Parcel
import android.os.Parcelable

class ProductoBDD(
var id: Int,
var nombre: String?,
var descripcion: String?,
var precio: String?,
var total: String?,
var fecha: String?,
var idProveedor: String?
):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String{
        return "${nombre} - ${precio}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeString(precio)
        parcel.writeString(total)
        parcel.writeString(fecha)
        parcel.writeString(idProveedor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductoBDD> {
        override fun createFromParcel(parcel: Parcel): ProductoBDD {
            return ProductoBDD(parcel)
        }

        override fun newArray(size: Int): Array<ProductoBDD?> {
            return arrayOfNulls(size)
        }
    }

}