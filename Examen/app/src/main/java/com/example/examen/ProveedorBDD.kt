package com.example.examen

import android.os.Parcel
import android.os.Parcelable

class ProveedorBDD(
    var id: Int,
    var nombre: String?,
    var cedula: String?,
    var sueldo: String?,
    var fecha: String?,
    var estado: String?
):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String{
        return "${nombre} - ${cedula}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(nombre)
        parcel?.writeString(cedula)
        parcel?.writeString(sueldo)
        parcel?.writeString(fecha)
        parcel?.writeString(estado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProveedorBDD> {
        override fun createFromParcel(parcel: Parcel): ProveedorBDD {
            return ProveedorBDD(parcel)
        }

        override fun newArray(size: Int): Array<ProveedorBDD?> {
            return arrayOfNulls(size)
        }
    }
}