package com.juanparedes.pruebameli.domain.usecase

import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class SearchProductsUseCase(
    private val repository: ProductsRepository
) {

    fun execute(productName: String) =
        repository.searchProducts(productName)
            .observeOn(AndroidSchedulers.mainThread())
}