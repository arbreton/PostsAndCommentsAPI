package com.example.demo.models

import android.os.Parcel
import android.os.Parcelable

class Post() : Parcelable {

    var userId: Int = 0
    var id: Int = 0
    var title: String? = ""
    var body: String? = ""
    var isTitle: Boolean = false

    constructor(parcel: Parcel) : this() {
        userId = parcel.readInt()
        id = parcel.readInt()
        title = parcel.readString()
        body = parcel.readString()
        isTitle = parcel.readInt() != 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(userId)
        dest?.writeInt(id)
        dest?.writeString(title)
        dest?.writeString(body)
        dest?.writeInt((if (isTitle) 1 else 0))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}