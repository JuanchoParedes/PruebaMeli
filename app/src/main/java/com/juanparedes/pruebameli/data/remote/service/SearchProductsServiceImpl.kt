package com.juanparedes.pruebameli.data.remote.service

import com.juanparedes.pruebameli.data.remote.api.SearchProductApiClient
import com.juanparedes.pruebameli.data.remote.entity.ResultProductApiDto
import com.juanparedes.pruebameli.domain.service.SearchProductsService
import io.reactivex.rxjava3.core.Flowable

class SearchProductsServiceImpl(
    private val client: SearchProductApiClient
): SearchProductsService {
    override fun searchProductsByName(productName: String): Flowable<List<ResultProductApiDto>> {
        return client.searchProductsByName(productName)
            .map { it.results }
    }
}