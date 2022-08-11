package com.example.mi_proyecto.database

import androidx.room.*
import com.example.mi_proyecto.clases.Entities.Producto

@Dao
interface productDao {

    @Query("SELECT * FROM producto ORDER BY id")
    fun loadAllProducts(): MutableList<Producto?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(producto: Producto?)

    @Update
    fun updateProduct(producto: Producto?)

    @Delete
    fun delete(producto: Producto?)

    @Query("SELECT * FROM producto WHERE id = :id")
    fun loadProductById(id: Int): Producto?

    @Query("DELETE FROM producto WHERE id = :id")
    fun deleteById(id: Int?)
}