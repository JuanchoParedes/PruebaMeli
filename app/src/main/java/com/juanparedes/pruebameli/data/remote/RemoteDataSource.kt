package com.juanparedes.pruebameli.data.remote

import com.juanparedes.pruebameli.data.remote.entity.ResultProductApiDto
import com.juanparedes.pruebameli.domain.service.SearchProductsService
import io.reactivex.rxjava3.core.Flowable

class RemoteDataSource(
    private val searchProductsService: SearchProductsService
) {

    fun searchProductsByName(productName: String): Flowable<List<ResultProductApiDto>> {
        return searchProductsService.searchProductsByName(productName)
    }
}