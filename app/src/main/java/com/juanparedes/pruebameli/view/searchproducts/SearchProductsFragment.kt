package com.juanparedes.pruebameli.view.searchproducts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanparedes.pruebameli.R
import com.juanparedes.pruebameli.databinding.FragmentSearchProductsBinding
import com.juanparedes.pruebameli.di.ComponentProvider
import com.juanparedes.pruebameli.view.adapter.ProductsAdapter
import com.juanparedes.pruebameli.view.model.SearchProductState
import javax.inject.Inject

class SearchProductsFragment : Fragment() {

    @Inject
    lateinit var factory: SearchProductsViewModelFactory
    private val viewModel: SearchProductsViewModel by viewModels { factory }

    private lateinit var binding: FragmentSearchProductsBinding

    private val productsAdapter: ProductsAdapter = ProductsAdapter {
        navigateToDetailFragment(it.id)
    }

    private val searchBarListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                viewModel.searchProducts(it)
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            (this.application as ComponentProvider).getComponent()
                .inject(this@SearchProductsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchProductsBinding.inflate(inflater, container, false)
        binding.rvProducts.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.searchBar.setOnQueryTextListener(searchBarListener)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getProductsListLiveData().observe(viewLifecycleOwner, Observer {
            onSearchProductState(it)
        })
    }

    private fun onSearchProductState(productState: SearchProductState) {
        when (productState) {
            is SearchProductState.InitialState -> onInitialState()
            is SearchProductState.SearchResults -> {
                onSearchResults()
                productsAdapter.submitList(productState.productsList)
            }
            is SearchProductState.ErrorState -> onErrorState()
            is SearchProductState.LoadingState -> onLoadingState()
            is SearchProductState.EmptyState -> onEmptyState()
        }
    }

    private fun onInitialState() {
        with(binding){
            tvEmptyResults.isVisible = true
            tvEmptyResults.text = getString(R.string.initial_state)
            progressBar.isVisible = false
            rvProducts.isVisible = false
        }
    }

    private fun onLoadingState() {
        with(binding){
            tvEmptyResults.isVisible = false
            progressBar.isVisible = true
            rvProducts.isVisible = false
        }
    }

    private fun onEmptyState() {
        with(binding){
            tvEmptyResults.isVisible = true
            tvEmptyResults.text = getString(R.string.empty_results)
            progressBar.isVisible = false
            rvProducts.isVisible = false
        }
    }

    private fun onErrorState() {
        with(binding){
            tvEmptyResults.isVisible = true
            tvEmptyResults.text = getString(R.string.error_state)
            progressBar.isVisible = false
            rvProducts.isVisible = false
        }
    }

    private fun onSearchResults() {
        with(binding){
            tvEmptyResults.isVisible = false
            progressBar.isVisible = false
            rvProducts.isVisible = true
        }
    }

    private fun navigateToDetailFragment(productId: String) {
        findNavController().navigate(
            SearchProductsFragmentDirections
                .actionSearchProductsFragmentToProductDetailFragment(
                    productId
                )
        )
    }

}