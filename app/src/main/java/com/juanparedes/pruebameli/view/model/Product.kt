package com.juanparedes.pruebameli.view.model

import com.juanparedes.pruebameli.domain.model.ResultProduct

data class Product(
    val id: String,
    val title: String,
    val thumbnail: String,
    val price: String
)

fun ResultProduct.mapToPresentation() = Product(
    id, title, thumbnail, price.toString()
)

fun List<ResultProduct>.mapToPresentation() = this.map { it.mapToPresentation() }