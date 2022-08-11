package com.example.mi_proyecto.clases.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")
class Producto(id : Int, marca: String, modelo: String, tipo: String, clase: String, consumo:Int, volRef:Int, volCong4:Int,
               volCong2:Int, estrellas:String, escarcha:String, autonomia:Int, capaCong:Double, clima:String, ruido:Int, urlImage:String) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public var id: Int = 0

    @ColumnInfo(name = "marca")
    public var marca: String

    @ColumnInfo(name = "modelo")
    public var modelo: String

    @ColumnInfo(name = "tipo")
    public var tipo: String

    @ColumnInfo(name = "clase")
    public var clase: String

    @ColumnInfo(name = "consumo")
    public var consumo: Int

    @ColumnInfo(name = "volRef")
    public var volRef: Int

    @ColumnInfo(name = "volCong4")
    public var volCong4: Int

    @ColumnInfo(name = "volCong2")
    public var volCong2: Int

    @ColumnInfo(name = "estrellas")
    public var estrellas: String

    @ColumnInfo(name = "escarcha")
    public var escarcha: String

    @ColumnInfo(name = "autonomia")
    public var autonomia: Int

    @ColumnInfo(name = "capaCong")
    public var capaCong: Double

    @ColumnInfo(name = "clima")
    public var clima: String

    @ColumnInfo(name = "ruido")
    public var ruido: Int

    @ColumnInfo(name = "urlImage")
    public var urlImage: String


    init {
        this.id = id
        this.marca = marca
        this.modelo = modelo
        this.tipo = tipo
        this.clase = clase
        this.consumo = consumo
        this.volRef = volRef
        this.volCong4 = volCong4
        this.volCong2 = volCong2
        this.estrellas = estrellas
        this.escarcha = escarcha
        this.autonomia = autonomia
        this.capaCong = capaCong
        this.clima = clima
        this.ruido = ruido
        this.urlImage = urlImage
    }

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readInt()!!,
        source.readInt()!!,
        source.readInt()!!,
        source.readInt()!!,
        source.readString()!!,
        source.readString()!!,
        source.readInt()!!,
        source.readDouble()!!,
        source.readString()!!,
        source.readInt()!!,
        source.readString()!!

    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(marca)
        writeString(modelo)
        writeString(tipo)
        writeString(clase)
        writeInt(consumo)
        writeInt(volRef)
        writeInt(volCong4)
        writeInt(volCong2)
        writeString(estrellas)
        writeString(escarcha)
        writeInt(autonomia)
        writeDouble(capaCong)
        writeString(clima)
        writeInt(ruido)
        writeString(urlImage)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }


    }
}
