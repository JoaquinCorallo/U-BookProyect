package com.okbit.ubook.firestore

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Books : Parcelable {

    var title: String? = null
    var author: String? = null
    var cover: String? = null
    var coverurl: String? = null
    var category: String? = null
    var description: String? = null
    var condition: String? = null
    var contact: String? = null
    var price: String? = null // Double
    var isbn: String? = null // Int
    var language: String? = null
    var delivery: String? = null

    constructor()

    private constructor(parcel: Parcel) {
        title = parcel.readString()
        author = parcel.readString()
        cover = parcel.readString()
        coverurl = parcel.readString()
        category = parcel.readString()
        description = parcel.readString()
        condition = parcel.readString()
        contact = parcel.readString()
        price = parcel.readString()
        isbn = parcel.readString()
        language = parcel.readString()
        delivery = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(title)
        dest?.writeString(author)
        dest?.writeString(cover)
        dest?.writeString(coverurl)
        dest?.writeString(category)
        dest?.writeString(description)
        dest?.writeString(condition)
        dest?.writeString(contact)
        dest?.writeString(price)
        dest?.writeString(isbn)
        dest?.writeString(language)
        dest?.writeString(delivery)
    }

    companion object CREATOR : Parcelable.Creator<Books> {
        override fun createFromParcel(parcel: Parcel): Books {
            return Books(parcel)
        }

        override fun newArray(size: Int): Array<Books?> {
            return arrayOfNulls(size)
        }
    }
}