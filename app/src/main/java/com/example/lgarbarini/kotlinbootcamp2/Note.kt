package com.example.lgarbarini.kotlinbootcamp2

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by lgarbarini on 12/12/17.
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Note (val name: String, val detail: String, val createdDate: String) : Parcelable {
/*
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readSerializable() as Date)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(detail)
        parcel.writeSerializable(createdDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
 */
}