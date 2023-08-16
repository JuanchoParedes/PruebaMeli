package com.juanparedes.pruebameli.data.local

import com.juanparedes.pruebameli.data.local.dao.ResultProductsDao
import com.juanparedes.pruebameli.data.remote.entity.ResultProductApiDto
import com.juanparedes.pruebameli.data.remote.entity.mapToLocalEntity
import io.reactivex.rxjava3.core.Completable

class LocalDataSource(
    private val resultProductsDao: ResultProductsDao
) {

    fun insertResultProducts(results: List<ResultProductApiDto>): Completable {
        return Completable.fromAction {
            resultProductsDao.updateResultProducts(results.mapToLocalEntity())
        }
    }
}