package com.juanparedes.pruebameli.view.model

import com.juanparedes.pruebameli.domain.model.ResultProduct

data class Product(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: Double
)

fun ResultProduct.mapToPresentation() = Product(
    id, title, thumbnail, price
)

fun List<ResultProduct>.mapToPresentation() = this.map { it.mapToPresentation() }