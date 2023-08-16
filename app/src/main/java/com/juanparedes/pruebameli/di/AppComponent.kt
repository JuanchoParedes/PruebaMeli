package com.juanparedes.pruebameli.di

import com.juanparedes.pruebameli.PruebaMeliApplication
import com.juanparedes.pruebameli.view.MainActivity
import com.juanparedes.pruebameli.view.productdetail.ProductDetailFragment
import com.juanparedes.pruebameli.view.searchproducts.SearchProductsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiServiceModule::class,
        PresentationModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    fun inject(application: PruebaMeliApplication)

    fun inject(fragment: SearchProductsFragment)

    fun inject(fragment: ProductDetailFragment)

    fun inject(activity: MainActivity)
}