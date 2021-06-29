package com.example.core.network

/**
 * Different states for any network request
 */
sealed class NetworkState {

    /**
     * Success network state
     *
     * @param isAddtionalRequest Is additional request
     * @param isEmptyResponse Is the response body empty
     */
    data class Success(
        val isAddtionalRequest: Boolean = false,
        val isEmptyResponse: Boolean = false
    ) : NetworkState()

    /**
     * Loading network state
     *
     * @param isAdditionalRequest Is additional request
     */
    data class Loading(
        val isAdditionalRequest: Boolean = false
    ) : NetworkState()

    /**
     * Error network state
     *
     * @param isAdditionalRequest Is additional request
     */
    data class Error(
        val isAdditionalRequest: Boolean = false
    ) : NetworkState()

    // ======================================================================================================
    // Public helper methods
    // ======================================================================================================

    /**
     * Check if current network state is [Loading]
     *
     * @return true is network state is loading, else false
     */
    fun isLoading() = this is Loading

    /**
     * Check if current network state is [Success]
     *
     * @return true is network state is success, else false
     */
    fun isSuccess() = this is Success

    /**
     * Check if current network state is [Error]
     *
     * @return true is network state is error, else false
     */
    fun isError() = this is Error
}
