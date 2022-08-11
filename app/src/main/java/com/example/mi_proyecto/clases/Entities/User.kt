package com.example.mi_proyecto.clases.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User (  id : Int, username : String , email : String, password : String) : Parcelable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int

    @ColumnInfo(name = "username")
    var username : String

    @ColumnInfo(name = "email")
    var email : String

    @ColumnInfo(name = "password")
    var password : String

    init {
        this.id = id
        this.username = username
        this.email = email
        this.password = password

    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString()!!,
            source.readString()!!,
            source.readString()!!

    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest){
        writeInt(id)
        writeString(username)
        writeString(email)
        writeString(password)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}
