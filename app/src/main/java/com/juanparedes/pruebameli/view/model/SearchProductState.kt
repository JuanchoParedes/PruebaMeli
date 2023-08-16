package com.juanparedes.pruebameli.view.model

sealed class SearchProductState {
    object Loading: SearchProductState()
    data class SearchResults(val productsList: List<Product>): SearchProductState()
    object Error: SearchProductState()
}