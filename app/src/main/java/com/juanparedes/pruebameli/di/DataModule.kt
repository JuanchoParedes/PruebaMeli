package com.juanparedes.pruebameli.di

import android.app.Application
import androidx.room.Room
import com.juanparedes.pruebameli.data.local.LocalDataSource
import com.juanparedes.pruebameli.data.local.ProductsDataBase
import com.juanparedes.pruebameli.data.remote.RemoteDataSource
import com.juanparedes.pruebameli.data.repository.ProductsRepositoryImpl
import com.juanparedes.pruebameli.domain.repository.ProductsRepository
import com.juanparedes.pruebameli.domain.service.SearchProductsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class, ApiServiceModule::class])
class DataModule {

    @Singleton
    @Provides
    internal fun provideProductsDatabase(
        application: Application,
    ) = Room.inMemoryDatabaseBuilder(application, ProductsDataBase::class.java)
        .build()

    @Singleton
    @Provides
    fun provideLocalDataSource(
        dataBase: ProductsDataBase
    ): LocalDataSource = LocalDataSource (
        dataBase.resultProductsDao()
    )

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        service: SearchProductsService
    ): RemoteDataSource = RemoteDataSource (
       service
    )

    @Singleton
    @Provides
    fun provideProductsRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): ProductsRepository = ProductsRepositoryImpl(
        localDataSource, remoteDataSource
    )

}