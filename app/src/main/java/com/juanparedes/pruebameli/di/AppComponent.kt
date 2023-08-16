package com.juanparedes.pruebameli.di

import com.juanparedes.pruebameli.PruebaMeliApplication
import com.juanparedes.pruebameli.view.MainActivity
import com.juanparedes.pruebameli.view.SearchProductsFragment
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

    fun inject(activity: MainActivity)
}