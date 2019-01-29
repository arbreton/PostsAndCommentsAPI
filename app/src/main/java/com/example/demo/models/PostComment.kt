package com.example.demo.models

import android.os.Parcel
import android.os.Parcelable

class PostComment() : Parcelable {

    var postId: Int = 0
    var id: Int = 0
    var name: String? = ""
    var email: String? = ""
    var body: String? = ""
    var isTitle: Boolean = false

    constructor(parcel: Parcel) : this() {
        postId = parcel.readInt()
        id = parcel.readInt()
        name = parcel.readString()
        email = parcel.readString()
        body = parcel.readString()
        isTitle = parcel.readInt() != 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(postId)
        dest?.writeInt(id)
        dest?.writeString(name)
        dest?.writeString(email)
        dest?.writeString(body)
        dest?.writeInt((if (isTitle) 1 else 0))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostComment> {
        override fun createFromParcel(parcel: Parcel): PostComment {
            return PostComment(parcel)
        }

        override fun newArray(size: Int): Array<PostComment?> {
            return arrayOfNulls(size)
        }
    }
}