package com.example.core.network.responses

/**
 * Generic reponse class for the data returned by requests
 *
 * @param results The list of results obtained by the call
 */
data class DataResponse<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)
