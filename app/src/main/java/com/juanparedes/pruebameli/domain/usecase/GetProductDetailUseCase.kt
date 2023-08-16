package com.juanparedes.pruebameli.domain.usecase

import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import com.juanparedes.pruebameli.view.model.Product
import com.juanparedes.pruebameli.view.model.mapToPresentation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetProductDetailUseCase(
    private val productsRepository: ProductsRepository
) {

    fun execute(productId: String): Single<Product> {
        return productsRepository.getProductDetail(productId)
            .map { it.mapToPresentation() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}