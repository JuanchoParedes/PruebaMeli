package com.juanparedes.pruebameli.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanparedes.pruebameli.data.local.dao.ResultProductsDao
import com.juanparedes.pruebameli.data.local.entity.ResultProductEntity

@Database(
    entities = arrayOf(
        ResultProductEntity::class
    ),
    version = 1,
    exportSchema = false
)
abstract class ProductsDataBase : RoomDatabase() {
    abstract fun resultProductsDao(): ResultProductsDao
}