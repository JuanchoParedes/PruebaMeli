package com.juanparedes.pruebameli.data.local

import com.juanparedes.pruebameli.data.local.dao.ResultProductsDao
import com.juanparedes.pruebameli.data.local.entity.ResultProductEntity
import com.juanparedes.pruebameli.data.remote.entity.ResultProductApiDto
import com.juanparedes.pruebameli.data.remote.entity.mapToLocalEntity
import com.juanparedes.pruebameli.domain.model.ResultProduct
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LocalDataSource(
    private val resultProductsDao: ResultProductsDao
) {

    fun insertResultProducts(results: List<ResultProductApiDto>): Completable {
        return Completable.fromAction {
            resultProductsDao.updateResultProducts(results.mapToLocalEntity())
        }
    }

    fun getProductDetail(productId: String): Single<ResultProductEntity> {
        return resultProductsDao.getResultProduct(productId)
    }
}