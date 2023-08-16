package com.juanparedes.pruebameli.view.model

sealed class SearchProductState {
    object InitialState: SearchProductState()
    object LoadingState: SearchProductState()
    object EmptyState: SearchProductState()
    data class SearchResults(val productsList: List<Product>): SearchProductState()
    object ErrorState: SearchProductState()
}