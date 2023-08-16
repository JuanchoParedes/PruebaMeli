package com.juanparedes.pruebameli

import com.juanparedes.pruebameli.data.local.LocalDataSource
import com.juanparedes.pruebameli.data.local.dao.ResultProductsDao
import com.juanparedes.pruebameli.data.remote.RemoteDataSource
import com.juanparedes.pruebameli.data.repository.ProductsRepositoryImpl
import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class RepositoryTest {

    private lateinit var repository: ProductsRepository
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var resultProductsDao: ResultProductsDao

    @Before
    fun setUp() {
        localDataSource = mock(LocalDataSource::class.java)
        remoteDataSource = mock(RemoteDataSource::class.java)
        resultProductsDao = mock(ResultProductsDao::class.java)

        repository = ProductsRepositoryImpl(localDataSource, remoteDataSource)

        Mockito.`when`(localDataSource.getProductDetail(productId))
            .thenReturn(Single.just(productEntity))

        Mockito.`when`(remoteDataSource.searchProductsByName(productId))
            .thenReturn(Flowable.just(responseList))

        Mockito.`when`(repository.getProductDetail(productId))
            .thenReturn(Single.just(resultProduct))

        Mockito.`when`(resultProductsDao.getResultProduct(productId))
            .thenReturn(Single.just(productEntity))

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `test repository local data source`() {
        repository.getProductDetail(productId)
        Mockito.verify(localDataSource).getProductDetail(productId)
    }

    @Test
    fun `test repository remote data source`() {
        repository.searchProducts(productId)
        Mockito.verify(remoteDataSource).searchProductsByName(productId)
    }
}