package com.example.characterslist.ui.list

/**
 * Different interaction events for [CharactersListFragment]
 */
sealed class CharacterListViewEvent {

    /**
     * Open character detail view.
     *
     * @param id unique identifier of character
     */
    data class OpenCharacterDetail(val id: Long) : CharacterListViewEvent()

    /**
     * Retry adding new characters to characrters list
     */
    object RetryAddLoading : CharacterListViewEvent()

    /**
     * Refresh characters list
     */
    object RefreshCharacterList : CharacterListViewEvent()
}
