package com.juanparedes.pruebameli.view.searchproducts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juanparedes.pruebameli.domain.usecase.SearchProductsUseCase
import com.juanparedes.pruebameli.view.model.SearchProductState
import com.juanparedes.pruebameli.view.model.mapToPresentation
import io.reactivex.rxjava3.disposables.CompositeDisposable

private val TAG = SearchProductsViewModel::class.simpleName

class SearchProductsViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val productsLiveData = MutableLiveData<SearchProductState>(SearchProductState.InitialState)
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun searchProducts(productName: String) {
        compositeDisposable.add(
            searchProductsUseCase.execute(productName)
                .doOnSubscribe {
                    productsLiveData.value = SearchProductState.LoadingState
                }
                .doOnError {
                    productsLiveData.value = SearchProductState.ErrorState
                    Log.d(TAG, it.localizedMessage, it)
                }
                .subscribe {
                    if (it.isNotEmpty())
                        productsLiveData.value = SearchProductState.SearchResults(it.mapToPresentation())
                    else
                        productsLiveData.value = SearchProductState.EmptyState
                }
        )
    }

    fun getProductsListLiveData() = productsLiveData

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}