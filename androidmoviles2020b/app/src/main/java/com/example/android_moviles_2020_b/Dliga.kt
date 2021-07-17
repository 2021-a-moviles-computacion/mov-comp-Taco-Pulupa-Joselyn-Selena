package com.example.android_moviles_2020_b

import android.os.Parcel
import android.os.Parcelable

class Dliga (
    val nombre: String?,
    val descripcion: String?,
    val liga: Dliga? = null
        ): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Dliga::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flag: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel?.writeParcelable(liga, flag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dliga> {
        override fun createFromParcel(parcel: Parcel): Dliga {
            return Dliga(parcel)
        }

        override fun newArray(size: Int): Array<Dliga?> {
            return arrayOfNulls(size)
        }
    }

}