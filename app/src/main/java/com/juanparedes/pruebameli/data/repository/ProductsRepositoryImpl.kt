package com.juanparedes.pruebameli.data.repository

import com.juanparedes.pruebameli.data.local.LocalDataSource
import com.juanparedes.pruebameli.data.local.entity.mapToDomain
import com.juanparedes.pruebameli.data.remote.RemoteDataSource
import com.juanparedes.pruebameli.data.remote.entity.mapToDomain
import com.juanparedes.pruebameli.domain.model.ResultProduct
import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class ProductsRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ProductsRepository {

    override fun searchProducts(productName: String): Flowable<List<ResultProduct>> {
        return remoteDataSource.searchProductsByName(productName).flatMap { productsList ->
            localDataSource.insertResultProducts(productsList)
                .andThen(Flowable.just(productsList.mapToDomain()))
        }
    }

    override fun getProductDetail(productId: String): Single<ResultProduct> {
        return localDataSource.getProductDetail(productId).map { it.mapToDomain() }
    }
}