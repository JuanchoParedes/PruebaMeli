package com.juanparedes.pruebameli.view.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juanparedes.pruebameli.domain.usecase.GetProductDetailUseCase

class ProductDetailViewModelFactory(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            return ProductDetailViewModel(
                getProductDetailUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}