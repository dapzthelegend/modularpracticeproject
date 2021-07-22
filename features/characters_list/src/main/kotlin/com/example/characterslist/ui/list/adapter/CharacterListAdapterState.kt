package com.example.characterslist.ui.list.adapter

import com.vmadalin.commons.ui.base.BaseViewState

/**
 * Different states for [CharacterListAdapter]
 *
 * @see BaseViewState
 */
sealed class CharacterListAdapterState(
    val hasExtraRow: Boolean
) : BaseViewState {

    /**
     * Added new characters into the character list
     */
    object Added : CharacterListAdapterState(hasExtraRow = true)

    /**
     * Loading for new characters to add to list
     */
    object AddLoading : CharacterListAdapterState(hasExtraRow = true)

    /**
     * Error on adding new characters to list
     */
    object AddError : CharacterListAdapterState(hasExtraRow = true)

    /**
     * No more charcaters to add to list
     */
    object NoMore : CharacterListAdapterState(hasExtraRow = false)

    // =============================================================================================
    // Public helper methods
    // =============================================================================================

    /**
     * Check if current view state is [Added]
     *
     * @return True if view state is [Added], else false
     */
    fun isAdded() = this is Added

    /**
     * Check if current view state is [AddLoading]
     *
     * @return True if view state is [AddLoading], else false
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [AddError]
     *
     * @return True if view state is [AddError], else false
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMore]
     *
     * @return True if view state is [NoMore], else false
     */
    fun isNoMore() = this is NoMore
}
