package com.juanparedes.pruebameli.di

import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import com.juanparedes.pruebameli.domain.usecase.SearchProductsUseCase
import com.juanparedes.pruebameli.view.SearchProductsViewModelFactory
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
}