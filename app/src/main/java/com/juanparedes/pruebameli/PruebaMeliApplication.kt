package com.juanparedes.pruebameli

import android.app.Application
import com.juanparedes.pruebameli.di.ComponentProvider
import com.juanparedes.pruebameli.di.DaggerAppComponent
import com.juanparedes.pruebameli.di.AppComponent
import com.juanparedes.pruebameli.di.ApplicationModule

class PruebaMeliApplication : Application(), ComponentProvider {

    private lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    override fun getComponent(): AppComponent = component
}