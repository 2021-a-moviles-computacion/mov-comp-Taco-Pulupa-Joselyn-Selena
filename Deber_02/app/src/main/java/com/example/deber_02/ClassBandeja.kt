package com.example.deber_02

import android.os.Parcel
import android.os.Parcelable

class ClassBandeja(
    val titulo: String?,
    val description: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(description)
    }

    companion object CREATOR : Parcelable.Creator<ClassBandeja> {
        override fun createFromParcel(parcel: Parcel): ClassBandeja {
            return ClassBandeja(parcel)
        }

        override fun newArray(size: Int): Array<ClassBandeja?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String{
        return "${titulo}-${description}"
    }

}