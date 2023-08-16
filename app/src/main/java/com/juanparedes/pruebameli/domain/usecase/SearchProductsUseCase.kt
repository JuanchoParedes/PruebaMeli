package com.juanparedes.pruebameli.domain.usecase

import com.juanparedes.pruebameli.domain.model.ResultProduct
import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchProductsUseCase(
    private val repository: ProductsRepository
) {

    fun execute(productName: String): Flowable<List<ResultProduct>> =
        repository.searchProducts(productName)

}