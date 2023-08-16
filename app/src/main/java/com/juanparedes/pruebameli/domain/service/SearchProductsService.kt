package com.juanparedes.pruebameli.domain.service

import com.juanparedes.pruebameli.data.remote.entity.ResultProductApiDto
import io.reactivex.rxjava3.core.Flowable

interface SearchProductsService {

    fun searchProductsByName(productName: String): Flowable<List<ResultProductApiDto>>
}