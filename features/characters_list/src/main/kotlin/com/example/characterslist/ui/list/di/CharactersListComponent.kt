package com.example.characterslist.ui.list.di

import com.example.characterslist.ui.list.CharactersListFragment
import com.example.core.di.CoreComponent
import com.example.core.di.scopes.FeatureScope
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [CharactersListModule].
 *
 * @see Component
 */
@Component(
    dependencies = [CoreComponent::class],
    modules = [CharactersListModule::class]
)
@FeatureScope
interface CharactersListComponent {

    /**
     * Inject dependencies on component
     *
     * @param listFragment an instance of [CharactersListFragment]
     */
    fun inject(listFragment: CharactersListFragment)
}
