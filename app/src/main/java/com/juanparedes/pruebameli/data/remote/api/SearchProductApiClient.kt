package com.juanparedes.pruebameli.data.remote.api

import com.juanparedes.pruebameli.data.remote.entity.ResponseBodyWrapper
import io.reactivex.rxjava3.core.Flowable

class SearchProductApiClient(
    private val apiService: SearchProductsApiService
) {

    fun searchProductsByName(productName: String): Flowable<ResponseBodyWrapper> =
        apiService.searchProducts(productName)
}