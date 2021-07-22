package com.example.characterslist.ui.list

import com.vmadalin.commons.ui.base.BaseViewState

sealed class CharactersListViewState : BaseViewState {

    /**
     * Refreshing characters list.
     */
    object Refreshing : CharactersListViewState()

    /**
     * Loaded characters list.
     */
    object Loaded : CharactersListViewState()

    /**
     * Loading characters list.
     */
    object Loading : CharactersListViewState()

    /**
     * Loading on add more elements to characters list.
     */
    object AddLoading : CharactersListViewState()

    /**
     * Empty characters list
     */
    object Empty : CharactersListViewState()

    /**
     * Error on loading characters list.
     */
    object Error : CharactersListViewState()

    /**
     * Error on loading more elements to characters list
     */
    object AddError : CharactersListViewState()

    /**
     * No more elements to add to characters list.
     */
    object NoMoreElements : CharactersListViewState()

    // =============================================================================================
    // Public helper methods
    // =============================================================================================

    /**
     * Check if current view state is [Refreshing]
     *
     * @return True if view state is [Refreshing],  else false
     */
    fun isRefreshing() = this is Refreshing

    /**
     * Check if current view state is [Loaded]
     *
     * @return True if view state is [Loaded],  else false
     */
    fun isLoaded() = this is Loaded

    /**
     * Check if current view state is [Loading]
     *
     * @return True if view state is [Loading],  else false
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [AddLoading]
     *
     * @return True if view state is [AddLoading],  else false
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [Error]
     *
     * @return True if view state is [Error],  else false
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [AddError]
     *
     * @return True if view state is [AddError],  else false
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMoreElements]
     *
     * @return True if view state is [NoMoreElements],  else false
     */
    fun isNoMoreElements() = this is NoMoreElements

    /**
     * Check if current view state is [Empty]
     *
     * @return True if view state is [Empty],  else false
     */
    fun isEmpty() = this is Empty
}
