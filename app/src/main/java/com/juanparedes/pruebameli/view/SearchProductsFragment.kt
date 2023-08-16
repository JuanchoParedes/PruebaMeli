package com.juanparedes.pruebameli.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    private val productsAdapter: ProductsAdapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.run {
            (this.application as ComponentProvider).getComponent()
                .inject(this@SearchProductsFragment)
        }
        //viewModel.searchProducts("")
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getProductsListLiveData().observe(viewLifecycleOwner, Observer {
           onSearchProductState(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
    }

    private fun onSearchProductState(productState: SearchProductState) {
        when (productState) {
            is SearchProductState.SearchResults -> {
                binding.progressBar.isVisible = false
                productsAdapter.submitList(productState.productsList)
            }
            is SearchProductState.Error -> {
                binding.progressBar.isVisible = false
            }
            is SearchProductState.Loading -> {
                binding.progressBar.isVisible = true
            }
        }
    }

}