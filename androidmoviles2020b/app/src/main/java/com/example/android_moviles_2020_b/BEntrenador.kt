package com.example.android_moviles_2020_b

import android.os.Parcel
import android.os.Parcelable

class BEntrenador(
    val nombre: String?,
    val descripcion: String?,
    val liga: Dliga? = null,
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun toString(): String{
        return "${nombre} - ${descripcion}"
    }

    override fun describeContents(): Int {

        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flag: Int) {
 //       if(parcel != null) {
        //parcel?.writeString(nombre)
          //  parcel?.writeString(descripcion)
        //}
        parcel?.writeString(nombre)
        parcel?.writeString(descripcion)
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}