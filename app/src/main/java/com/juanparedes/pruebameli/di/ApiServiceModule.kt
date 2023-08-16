package com.juanparedes.pruebameli.di

import com.juanparedes.pruebameli.data.remote.api.SearchProductApiClient
import com.juanparedes.pruebameli.data.remote.api.SearchProductsApiService
import com.juanparedes.pruebameli.data.remote.service.SearchProductsServiceImpl
import com.juanparedes.pruebameli.domain.service.SearchProductsService
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiServiceModule {

    companion object {
        private const val BASE_URL = "https://api.mercadolibre.com/"
    }

    @Provides
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Provides
    internal fun provideHttpClient() = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    internal fun provideSearchProductApiClient(
        retrofit: Retrofit
    ): SearchProductApiClient = SearchProductApiClient(
        retrofit.create(SearchProductsApiService::class.java)
    )

    @Provides
    internal fun provideSearchProductsService(
        client: SearchProductApiClient
    ): SearchProductsService = SearchProductsServiceImpl(
        client
    )

}