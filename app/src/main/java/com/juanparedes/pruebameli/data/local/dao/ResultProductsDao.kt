package com.juanparedes.pruebameli.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.juanparedes.pruebameli.data.local.entity.ResultProductEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
abstract class ResultProductsDao {

    @Query("SELECT * FROM PRODUCTS")
    protected abstract fun getAllProducts(): List<ResultProductEntity>

    @Query("DELETE FROM PRODUCTS")
    protected abstract fun deleteAllProducts()

    @Query("SELECT * FROM PRODUCTS WHERE product_id = :productId")
    abstract fun getResultProduct(productId: String): Flowable<ResultProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertResultProducts(products: List<ResultProductEntity>)

    @Transaction
    open fun updateResultProducts(products: List<ResultProductEntity>) {
        deleteAllProducts()
        insertResultProducts(products)
    }

}