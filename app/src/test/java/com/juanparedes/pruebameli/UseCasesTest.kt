package com.juanparedes.pruebameli

import com.juanparedes.pruebameli.data.local.LocalDataSource
import com.juanparedes.pruebameli.data.remote.RemoteDataSource
import com.juanparedes.pruebameli.data.repository.ProductsRepositoryImpl
import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import com.juanparedes.pruebameli.domain.usecase.SearchProductsUseCase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class UseCasesTest {

    private lateinit var repository: ProductsRepository
    private lateinit var searchProductsUseCase: SearchProductsUseCase

    @Before
    fun setUp() {
        val localDataSource =  mock(LocalDataSource::class.java)
        val remoteDataSource =  mock(RemoteDataSource::class.java)
        repository = ProductsRepositoryImpl(
           localDataSource, remoteDataSource
        )

        Mockito.`when`(remoteDataSource.searchProductsByName(productId))
            .thenReturn(Flowable.just(responseList))

        Mockito.`when`(repository.searchProducts(productId))
            .thenReturn(Flowable.just(resultProductList))

        Mockito.`when`(localDataSource.insertResultProducts(responseList))
            .thenReturn(Completable.complete())
    }

    @Test
    fun textSearchProductsUseCase() {
        searchProductsUseCase = SearchProductsUseCase(repository)
        searchProductsUseCase.execute(productId)
            .test()
            .hasSubscription()

    }
}