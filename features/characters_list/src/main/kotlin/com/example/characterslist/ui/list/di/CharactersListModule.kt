package com.example.characterslist.ui.list.di

import com.example.characterslist.ui.list.CharactersListFragment
import com.example.characterslist.ui.list.CharactersListViewModel
import com.example.characterslist.ui.list.MarvelRepository
import com.example.characterslist.ui.list.adapter.CharacterListAdapter
import com.example.characterslist.ui.list.model.CharacterItemMapper
import com.example.core.di.scopes.FeatureScope
import com.example.core.network.services.MarvelService
import com.example.ui.extensions.viewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [CharactersListComponent]
 *
 * @see Module
 */
@Module
class CharactersListModule(
    private val fragment: CharactersListFragment
) {

    /**
     * Create provider method binding for [CharactersListViewModel]
     *
     * @return Instance of view model
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun provideCharactersListViewModel(
        repository: MarvelRepository
    ) = fragment.viewModel {
        CharactersListViewModel(repository)
    }

    /**
     * Create provider method binding for [MarvelRepository]
     *
     * @return Instance of [MarvelRepository]
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun provideMarvelRepository(
        service: MarvelService,
        mapper: CharacterItemMapper
    ) = MarvelRepository(service, mapper)

    /**
     * Create provider method binding for [CharacterItemMapper]
     *
     * @return Instance of [CharacterItemMapper]
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun provideCharacterItemMapper() = CharacterItemMapper()

    /**
     * Create provider method binding for [CharacterListAdapter]
     *
     * @return Instance of [CharacterListAdapter]
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun provideCharactersListAdapter(
        viewModel: CharactersListViewModel
    ) = CharacterListAdapter(viewModel)
}
