package com.juanparedes.pruebameli.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.juanparedes.pruebameli.domain.model.ResultProduct

@Entity(tableName = "PRODUCTS")
class ResultProductEntity {
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    var id: String = ""

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String = ""

    @ColumnInfo(name = "price")
    var price: Double = 0.0
}

fun ResultProductEntity.mapToDomain() =
    ResultProduct(
        this.id,
        this.title,
        this.thumbnail,
        this.price
    )