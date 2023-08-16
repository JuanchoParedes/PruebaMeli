package com.juanparedes.pruebameli.view.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.juanparedes.pruebameli.databinding.FragmentProductDetailBinding
import com.juanparedes.pruebameli.di.ComponentProvider
import com.juanparedes.pruebameli.view.loadImage
import javax.inject.Inject

class ProductDetailFragment : Fragment() {

    @Inject
    lateinit var factory: ProductDetailViewModelFactory
    private val viewModel: ProductDetailViewModel by viewModels { factory }

    private val args: ProductDetailFragmentArgs by navArgs()
    private val productId: String by lazy {
        args.productId
    }

    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            (this.application as ComponentProvider).getComponent()
                .inject(this@ProductDetailFragment)
        }
        viewModel.getProductDetail(productId)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getProductDetailLiveData().observe(viewLifecycleOwner) {
            binding.ivProductImage.loadImage(it.thumbnail)
            binding.tvProductDetailPrice.text = "$${it.price}"
            binding.tvProductDetailTitle.text = it.title
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}
