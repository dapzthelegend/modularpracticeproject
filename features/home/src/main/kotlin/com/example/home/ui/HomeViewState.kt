package com.example.home.ui

import com.vmadalin.commons.ui.base.BaseViewState

/**
 * Differeny states for [HomeFragment]
 *
 * @see BaseViewState
 */
sealed class HomeViewState : BaseViewState {

    /**
     * State for FullScreen
     */
    object FullScreen : HomeViewState()

    /**
     * State for navigation with bottom nav bar
     */
    object NavigationScreen : HomeViewState()

    // =============================================================================================
    // Public Helper Methods
    // =============================================================================================

    /**
     * Check if current view is [FullScreen]
     *
     * @return True if view is full screen view state, otherwise false
     */
    fun isFullScreen() = this is FullScreen

    /**
     * Check if current view is [NavigationScreen]
     *
     * @return True if view is navigation view state, otherwise false
     */
    fun isNavigationScreen() = this is NavigationScreen
}
