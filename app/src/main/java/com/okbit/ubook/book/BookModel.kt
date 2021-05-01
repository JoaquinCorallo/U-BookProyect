package com.okbit.ubook.book

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class BookModel : Parcelable {

    var title: String? = null
    var author: String? = null

    constructor()

    private constructor(parcel: Parcel) {
        title = parcel.readString()
        author = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(author)
    }

    companion object CREATOR : Parcelable.Creator<BookModel> {
        override fun createFromParcel(parcel: Parcel): BookModel {
            return BookModel(parcel)
        }

        override fun newArray(size: Int): Array<BookModel?> {
            return arrayOfNulls(size)
        }
    }
}