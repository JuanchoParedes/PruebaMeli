package com.juanparedes.pruebameli.domain.repository

import com.juanparedes.pruebameli.domain.model.ResultProduct
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface ProductsRepository {

    fun searchProducts(productName: String): Flowable<List<ResultProduct>>

    fun getProductDetail(productId: String): Single<ResultProduct>
}