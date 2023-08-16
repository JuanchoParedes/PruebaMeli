package com.juanparedes.pruebameli.view.searchproducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juanparedes.pruebameli.domain.usecase.SearchProductsUseCase

class SearchProductsViewModelFactory(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchProductsViewModel::class.java)) {
            return SearchProductsViewModel(
                searchProductsUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}