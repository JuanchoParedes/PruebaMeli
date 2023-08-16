package com.juanparedes.pruebameli.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juanparedes.pruebameli.databinding.ProductItemViewBinding
import com.juanparedes.pruebameli.view.loadImage
import com.juanparedes.pruebameli.view.model.Product

class ProductsAdapter(
    private val onItemClick: (Product) -> Unit
): ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ProductItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class ProductsViewHolder(private val binding: ProductItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product, onItemClick: (Product) -> Unit) {
            binding.apply {
                tvProductTitle.text = item.title
                tvProductPrice.text = "$${item.price}"
                ivProductImage.loadImage(item.thumbnail)
                root.setOnClickListener {
                    onItemClick.invoke(item)
                }
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
