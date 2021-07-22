package com.example.home.ui.di

import com.example.core.di.CoreComponent
import com.example.core.di.scopes.FeatureScope
import com.example.home.ui.HomeFragment
import dagger.Component

/**
 * Dependency graph for Home Feature
 *
 * @see Component
 */
@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [HomeModule::class]
)
interface HomeComponent {

    /**
     * Inject dependencies on component
     *
     * @param homeFragment Home Component
     */
    fun inject(homeFragment: HomeFragment)
}
