package com.example.home.ui.di

import com.example.core.di.scopes.FeatureScope
import com.example.home.ui.HomeFragment
import com.example.home.ui.HomeViewModel
import com.example.ui.extensions.viewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the dependency graph [HomeComponent]
 *
 * @see Module
 */
@Module
class HomeModule(
    val fragment: HomeFragment
) {

    /**
     * Create a provider method binding for [HomeViewModel]
     *
     * @return Instance of HomeViewModel
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesHomeViewModel() = fragment.viewModel {
        HomeViewModel()
    }
}
