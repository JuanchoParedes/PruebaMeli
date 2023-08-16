package com.juanparedes.pruebameli.view.productdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juanparedes.pruebameli.domain.usecase.GetProductDetailUseCase
import com.juanparedes.pruebameli.view.model.Product
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ProductDetailViewModel(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {

    private val productDetailLiveData = MutableLiveData<Product>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getProductDetail(productId: String) {
        compositeDisposable.add(
            getProductDetailUseCase.execute(productId)
                .doOnSuccess {
                    productDetailLiveData.value = it
                }
                .subscribe()
        )
    }

    fun getProductDetailLiveData() = productDetailLiveData

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}