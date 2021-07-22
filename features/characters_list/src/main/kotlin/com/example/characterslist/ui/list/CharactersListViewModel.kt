package com.example.characterslist.ui.list

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.characterslist.ui.list.model.CharacterItem
import com.example.commons.ui.livedata.SingleLiveData
import com.example.core.network.NetworkState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CharactersListViewModel @Inject constructor(
    val repository: MarvelRepository
) : ViewModel() {

    val networkState = repository.pageDataSource.value?.networkState!!

    val event = SingleLiveData<CharacterListViewEvent>()
    val data: Flow<PagingData<CharacterItem>> = repository.getCharacters()
        .cachedIn(viewModelScope)
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAddtionalRequest && it.isEmptyResponse) {
                    CharactersListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    CharactersListViewState.Empty
                } else {
                    CharactersListViewState.Loaded
                }
            is NetworkState.Loading -> {
                if (it.isAdditionalRequest) {
                    CharactersListViewState.AddLoading
                } else {
                    CharactersListViewState.Loading
                }
            }
            is NetworkState.Error ->
                if (it.isAdditionalRequest) {
                    CharactersListViewState.AddError
                } else {
                    CharactersListViewState.Error
                }
        }
    }

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Called to refresh the view adapter
     */
    fun refreshLoadedCharactersList() {
        event.postValue(CharacterListViewEvent.RefreshCharacterList)
    }

    /**
     * Retry last fetch operation to add characters into list.
     */
    fun retryAddCharactersList() {
        event.postValue(CharacterListViewEvent.RetryAddLoading)
    }

    /**
     * Send interaction event for open character detail view from selected character.
     *
     * @param characterId Character identifier.
     */
    fun openCharacterDetail(characterId: Long) {
        event.postValue(CharacterListViewEvent.OpenCharacterDetail(characterId))
    }
}
