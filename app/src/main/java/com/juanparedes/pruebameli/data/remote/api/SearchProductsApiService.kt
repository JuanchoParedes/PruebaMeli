package com.juanparedes.pruebameli.data.remote.api

import com.juanparedes.pruebameli.data.remote.entity.ResponseBodyWrapper
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchProductsApiService {

    @GET("/sites/MLA/search")
    fun searchProducts(
        @Query("q") productName: String
    ): Flowable<ResponseBodyWrapper>
}