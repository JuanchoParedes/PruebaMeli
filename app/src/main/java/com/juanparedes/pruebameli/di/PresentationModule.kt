package com.juanparedes.pruebameli.di

import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import com.juanparedes.pruebameli.domain.usecase.GetProductDetailUseCase
import com.juanparedes.pruebameli.domain.usecase.SearchProductsUseCase
import com.juanparedes.pruebameli.view.productdetail.ProductDetailViewModelFactory
import com.juanparedes.pruebameli.view.searchproducts.SearchProductsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class PresentationModule {

    @Singleton
    @Provides
    internal fun provideSearchProductsUseCase(
        repository: ProductsRepository
    ): SearchProductsUseCase = SearchProductsUseCase(
        repository
    )

    @Singleton
    @Provides
    internal fun provideSearchProductsViewModelFactory(
        searchProductsUseCase: SearchProductsUseCase
    ): SearchProductsViewModelFactory = SearchProductsViewModelFactory(
        searchProductsUseCase
    )

    @Singleton
    @Provides
    internal fun provideGetProductDetailUseCase(
        repository: ProductsRepository
    ): GetProductDetailUseCase = GetProductDetailUseCase(
        repository
    )

    @Singleton
    @Provides
    internal fun provideProductDetailViewModelFactory(
        getProductDetailUseCase: GetProductDetailUseCase
    ): ProductDetailViewModelFactory = ProductDetailViewModelFactory(
        getProductDetailUseCase
    )
}