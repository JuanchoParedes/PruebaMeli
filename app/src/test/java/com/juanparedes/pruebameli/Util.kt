package com.juanparedes.pruebameli

import com.juanparedes.pruebameli.data.local.entity.ResultProductEntity
import com.juanparedes.pruebameli.data.remote.entity.ResultProductApiDto
import com.juanparedes.pruebameli.domain.model.ResultProduct
import com.juanparedes.pruebameli.view.model.Product


val productId = "123"

val presentationProduct = Product("123", "zapatos", "URL", "60.0")

val resultProduct = ResultProduct("123", "zapatos", "URL", 60.0)

val response = ResultProductApiDto("123", "zapatos", "URL", 60.0)

val responseList = listOf(response)

val resultProductList = listOf(resultProduct)

val productEntity = ResultProductEntity().apply {
    id = "123"
    title = "zapatos"
    thumbnail = "URL"
    price = 60.0
}