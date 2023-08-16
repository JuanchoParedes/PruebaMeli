package com.juanparedes.pruebameli.domain.usecase

import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchProductsUseCase(
    private val repository: ProductsRepository
) {

    fun execute(productName: String) =
        repository.searchProducts(productName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}